package de.metafinanz.mixnmatch.backend.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import de.metafinanz.mixnmatch.backend.dao.MixandmatchDao;
import de.metafinanz.mixnmatch.backend.model.EventRequest;
import de.metafinanz.mixnmatch.backend.model.Match;

@Controller
@RequestMapping("/requests")
public class EventRequestController {
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

	private MixandmatchDao dao;

	public MixandmatchDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(MixandmatchDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "/dirty", method = { RequestMethod.GET })
	public String createRequestGET(@RequestParam String locationKey,
			@RequestParam String date, @RequestParam String userid)
			throws ParseException {
		EventRequest eventRequest = new EventRequest();
		eventRequest.setDate(simpleDateFormat.parse(date));
		eventRequest.setLocationKey(locationKey);
		eventRequest.setUserid(userid);
		return createRequest(eventRequest);
	}

	@RequestMapping(method = { RequestMethod.POST })
	public String createRequest(@RequestBody EventRequest request) {
		String locationKey = request.getLocationKey();
		if (request.getDate() == null) {
			request.setDate(new Date());
		}
		String date = simpleDateFormat.format(request.getDate());
		String userid = request.getUserid();
		String key = createUrl(locationKey, date, userid);
		request.setUrl(key);
		dao.saveRequest(request);
		return "redirect:" + key;
	}

	@RequestMapping(method = { RequestMethod.GET })
	public @ResponseBody
	Collection<EventRequest> listAllRequests() throws Exception {
		return dao.getAllRequests();
	}

	public static String createUrl(String locationKey, String date,
			String userid) {
		StringBuilder keyBuilder = new StringBuilder("/requests/");
		keyBuilder.append(locationKey);
		keyBuilder.append("/");
		keyBuilder.append(date);
		keyBuilder.append("/lunch/");
		if (userid != null) {
			keyBuilder.append(userid);
		}
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
		EventRequest eventRequest = dao.getRequest(location, date, user);
		if (eventRequest == null) {
			throw new EventNotFoundException(createUrl(location, date, user));
		}
		return eventRequest;
	}

	@RequestMapping(value = { "/{location}/{date}", "/{location}/{date}/lunch" }, method = { RequestMethod.GET })
	public @ResponseBody
	Collection<EventRequest> getRequestByLocationAndDate(
			@PathVariable String location, @PathVariable String date) {
		return dao.getRequestsByLocationAndDate(location, date);
	}
	
	@RequestMapping(value = { "/{location}" }, method = { RequestMethod.GET })
	public @ResponseBody
	Collection<EventRequest> getRequestByLocation(
			@PathVariable String location) {
		return dao.getRequestsByLocation(location);
	}

}
