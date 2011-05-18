package de.metafinanz.mixnmatch.backend.rest;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import de.metafinanz.mixnmatch.backend.dao.MixandmatchDao;
import de.metafinanz.mixnmatch.backend.model.EventRequest;

@Controller
@RequestMapping("/requests")
public class EventRequestController {
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

	
	private Map<String, EventRequest> requests = new HashMap<String, EventRequest>();
	private MixandmatchDao dao;
	
	public MixandmatchDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(MixandmatchDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method = { RequestMethod.POST })
	public String createRequest(@RequestBody EventRequest request,
			WebRequest webRequest) {
		String locationKey = request.getLocationKey();
		String date = simpleDateFormat.format(request.getDate());
		String userid = request.getUserid();
		String key = createUrl(locationKey, date, userid);
		request.setUrl(key);
		requests.put(key, request);
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("locationKey", locationKey);
		vars.put("date", date);
		vars.put("userid", userid);
		UUID uuid = java.util.UUID.randomUUID();
		String url = "http://localhost:5984/requests/" + uuid + "/" + locationKey + "/" + date + "/" + userid;
		restTemplate.put(url, request);
		return "redirect:" + key;
	}

	@RequestMapping(method = { RequestMethod.GET })
	public @ResponseBody
	Collection<EventRequest> listAllRequests() {
		List<EventRequest> list = dao.getAllRequests();
		if (list != null)
		{
			return list;
		}
		return requests.values();
	}

	private String createUrl(String locationKey, String date, String userid) {
		StringBuilder keyBuilder = new StringBuilder("/requests/");
		keyBuilder.append(locationKey);
		keyBuilder.append("/");
		keyBuilder.append(date);
		keyBuilder.append("/lunch/");
		keyBuilder.append(userid);
		String key = keyBuilder.toString();
		return key;
	}

	@ExceptionHandler(EventNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleEventNotFound(EventNotFoundException e) {

	}

	@RequestMapping(value = "/{location}/{date}/lunch/{user}", method = { RequestMethod.GET })
	public @ResponseBody
	EventRequest getRequest(@PathVariable String location,
			@PathVariable String date, @PathVariable String user)
			throws EventNotFoundException {
		String url = createUrl(location, date, user);
		EventRequest eventRequest = requests.get(url);
		if (eventRequest == null) {
			throw new EventNotFoundException(url);
		}
		return eventRequest;
	}
}
