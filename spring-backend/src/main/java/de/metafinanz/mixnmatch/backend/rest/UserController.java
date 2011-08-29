package de.metafinanz.mixnmatch.backend.rest;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.metafinanz.mixnmatch.backend.dao.MixandmatchDao;
import de.metafinanz.mixnmatch.backend.model.EventRequest;
import de.metafinanz.mixnmatch.backend.model.Match;

@Controller
@RequestMapping("/users")
public class UserController {
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

	private MixandmatchDao dao;

	public MixandmatchDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(MixandmatchDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "/{user}", method = { RequestMethod.GET })
	public @ResponseBody
	Collection<? extends EventRequest> getMyRequests(@PathVariable String user) {
		Collection<? extends EventRequest> events = dao.getRequestsByUser(user);

		for (EventRequest eventRequest : events) {
			eventRequest.setMatchUrl(EventRequestController.createUrl(
					eventRequest.getLocationKey(),
					simpleDateFormat.format(eventRequest.getDate()), null));
		}
		return events;
	}
	
	@RequestMapping(value = "/{user}/matches", method = { RequestMethod.GET })
	public @ResponseBody Collection<EventRequest> getMyMatches(@PathVariable String user){
		Collection<EventRequest> myMatches = new LinkedList<EventRequest>();
		Collection<? extends EventRequest> events = dao.getRequestsByUser(user);
		for (EventRequest eventRequest : events) {
			String locationKey = eventRequest.getLocationKey();
			Date date = eventRequest.getDate();
			Collection<? extends EventRequest> matches = dao.listMatches(locationKey.toLowerCase(), simpleDateFormat.format(date));
			for (EventRequest match : matches) {
				myMatches.add(match);
			}
		}
		return myMatches;
	}
}
