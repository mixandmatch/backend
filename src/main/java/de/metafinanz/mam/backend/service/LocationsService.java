package de.metafinanz.mam.backend.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.LocationsController;

@Component
@Path("/locations")
public class LocationsService {

	@Autowired
	LocationsController locationsController;

	@GET
	public Response locations() {
		String result = locationsController.getLocations();
		return Response.status(200).entity(result).build();
	}

}