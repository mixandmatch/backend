package de.metafinanz.mam.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.OfficeController;
import de.metafinanz.mam.backend.repository.Office;

@Component
@Path("office")
public class OfficeService implements ILocationService<Office> {

	@Autowired
	OfficeController locationsController;

	static Logger logger = LoggerFactory.getLogger(OfficeService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Office> locations() {
		logger.trace("entering office locations()");
		return locationsController.getLocations();
		// return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Office getLocation(@PathParam("id") String id) {
		logger.trace("entering getLocation");
		logger.debug("find Location with id: " + id);
		return locationsController.getLocation(new Long(id));
	}

	/**
	 * EXAMPLE-JSON:<br/><br/>
	 * 
	 * <code>{"locationName":"Kantine 12","logitude":11.6491052,"latitude":48.1884351}</code>
	 * 
	 * @param aLocation
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response locationAdd(Office aLocation) {
		logger.trace("entering locationAdd");
		if (aLocation != null) {
			logger.debug("Adding new location with name: " + aLocation.getName());
		}

		boolean result = false;

		try {
			result = locationsController.addLocation(aLocation);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (result) {
			return Response.ok().status(Status.CREATED).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}
    
	@DELETE
    @Path("{id}")
	@Override
    public Response deleteLocation(@PathParam("id") Long id) {
        logger.trace("entering deleteLocation");
        boolean deleted = locationsController.removeLocation(id);
        return deleted ?
                Response.ok().build() :
                Response.status(Status.NOT_FOUND).build();
    }

}