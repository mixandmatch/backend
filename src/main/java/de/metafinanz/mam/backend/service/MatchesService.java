package de.metafinanz.mam.backend.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.MatchesController;

@Component
@Path("/matches")
public class MatchesService {

	@Autowired
	MatchesController matchesController;

	@GET
	public Response matches() {
		String result = matchesController.getMatches();
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/add")
	public Response matches_add() {
		String result = matchesController.addMatch();
		return Response.status(200).entity(result).build();
	}

}