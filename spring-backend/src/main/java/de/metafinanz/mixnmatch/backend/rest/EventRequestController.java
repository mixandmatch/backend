package de.metafinanz.mixnmatch.backend.rest;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import de.metafinanz.mixnmatch.backend.model.EventRequest;

@Controller
@RequestMapping("/requests")
public class EventRequestController {
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

	private Map<String, EventRequest> requests = new HashMap<String, EventRequest>();

	@RequestMapping(method = { RequestMethod.POST })
	public String createRequest(@RequestBody EventRequest request,
			WebRequest webRequest) {
		String locationKey = request.getLocationKey();
		String date = simpleDateFormat.format(request.getDate());
		String userid = request.getUserid();
		String key = createUrl(locationKey, date, userid);
		requests.put(key, request);
		return "redirect:" + key;
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
	public void handleEventNotFound(EventNotFoundException e){
		
	}

	@RequestMapping(value = "/{location}/{date}/lunch/{user}", method = { RequestMethod.GET })
	public @ResponseBody
	EventRequest getRequest(@PathVariable String location,
			@PathVariable String date, @PathVariable String user) throws EventNotFoundException {
		String url = createUrl(location, date, user);
		EventRequest eventRequest = requests.get(url);
		if(eventRequest == null){
			throw new EventNotFoundException(url);
		}
		return eventRequest;
	}
}
