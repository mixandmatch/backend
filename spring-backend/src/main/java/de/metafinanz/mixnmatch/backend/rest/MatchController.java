package de.metafinanz.mixnmatch.backend.rest;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.metafinanz.mixnmatch.backend.dao.MixandmatchDao;
import de.metafinanz.mixnmatch.backend.model.EventRequest;

@Controller
@RequestMapping("/matches")
public class MatchController {
	@Autowired
	MixandmatchDao dao;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Collection<? extends EventRequest> listAllMatches() {
		return dao.listAllMatches();
	}

}
