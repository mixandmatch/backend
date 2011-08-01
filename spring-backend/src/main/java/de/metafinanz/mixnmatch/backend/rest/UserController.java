package de.metafinanz.mixnmatch.backend.rest;

import java.text.SimpleDateFormat;
import java.util.Collection;

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
	Collection<EventRequest> getMyRequests(@PathVariable String user) {
		Collection<EventRequest> events = dao.getRequestsByUser(user);

		for (EventRequest eventRequest : events) {
			eventRequest.setMatchUrl(EventRequestController.createUrl(
					eventRequest.getLocationKey(),
					simpleDateFormat.format(eventRequest.getDate()), null));
		}
		return events;
	}
}
