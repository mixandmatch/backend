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

import de.metafinanz.mam.backend.controller.CanteenController;
import de.metafinanz.mam.backend.repository.Canteen;
import de.metafinanz.mam.backend.repository.Location;

@Component
@Path("canteen")
public class CanteenService implements ILocationService<Canteen> {

	@Autowired
	CanteenController locationsController;

	static Logger logger = LoggerFactory.getLogger(CanteenService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response locations() {
		logger.trace("entering locations()");
		List<Canteen> result = locationsController.getLocations();
		//http://stackoverflow.com/questions/6081546/jersey-can-produce-listt-but-cannot-response-oklistt-build
		return Response.ok().type(MediaType.APPLICATION_JSON).entity(result.toArray(new Canteen[result.size()])).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocation(@PathParam("id") String id) {
		logger.trace("entering getLocation");
		logger.debug("find Location with id: " + id);
		Canteen result = locationsController.getLocation(new Long(id));
		return Response.ok().type(MediaType.APPLICATION_JSON).entity(result).build();
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
	public Response locationAdd(Canteen aLocation) {
		logger.trace("entering locationAdd");
		if (aLocation != null) {
			logger.debug("Adding new location with name: " + aLocation.getName());
		}

		Location result = null;

		try {
			result = locationsController.addLocation(aLocation);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (result != null) {
			return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON).entity(result).build();
		}
		return Response.status(Status.CONFLICT).build();
	}
    
	@DELETE
    @Path("{id}")
    public Response deleteLocation(@PathParam("id") Long id) {
        logger.trace("entering deleteLocation");
        boolean deleted = locationsController.removeLocation(id);
        return deleted ?
                Response.ok().build() :
                Response.status(Status.NOT_FOUND).build();
    }

}