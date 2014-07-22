package de.metafinanz.mam.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	/**
	 * Gets all canteen locations.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response locations() {
		logger.trace("entering locations()");
		List<Canteen> result = locationsController.getLocations();
		//http://stackoverflow.com/questions/6081546/jersey-can-produce-listt-but-cannot-response-oklistt-build
		return Response.ok().type(MediaType.APPLICATION_JSON).entity(result.toArray(new Canteen[result.size()])).build();
	}

	/**
	 * Get's a canteen location by it's id or 404 if the canteen could not be found.
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocation(@PathParam("id") String id) {
		logger.trace("entering getLocation");
		logger.debug("find Location with id: {}", id);
		Canteen result = locationsController.getLocation(new Long(id));
		if (result != null) {
			return Response.ok().type(MediaType.APPLICATION_JSON).entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
		
	}

	/**
	 * Adds a canteen location.
	 * 
	 * EXAMPLE-JSON:<br/><br/>
	 * 
	 * <code>{"locationName":"Kantine 12","logitude":11.6491052,"latitude":48.1884351}</code>
	 * 
	 * @param aLocation
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response locationAdd(Canteen aLocation) {
		logger.trace("entering locationAdd");
		if (aLocation != null) {
			logger.debug("Adding new location with name: {}", aLocation.getName());
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
	
    
	/**
	 * Deletes a canteen location by it's id if found. If not then 404 is returned.
	 * 
	 */
	@DELETE
    @Path("{id}")
    public Response deleteLocation(@PathParam("id") Long id) {
        logger.trace("entering deleteLocation");
        boolean deleted = locationsController.removeLocation(id);
        return deleted ?
                Response.ok().build() :
                Response.status(Status.NOT_FOUND).build();
    }
	
	/**
	 * Gets all canteen locations that are near the specified office (by it's id). The maximum distance is 1000 meters. 
	 * @param id of the office
	 * @return List of canteen locations or 404 if office was not found.
	 */
	@GET
	@Path("byOffice")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByOffice(@QueryParam("id") Long id) {
		logger.trace("entering getByOffice");

		List<Canteen> result;
		try {
			result = locationsController.getLocationByOffice(id);
		} catch (IllegalArgumentException e) {
			logger.warn("Could not get canteens for office "+id+".", e);
			return Response.status(Status.NOT_FOUND).build();
		}
		if (result == null) {
			result = new ArrayList<Canteen>();
		}
		//http://stackoverflow.com/questions/6081546/jersey-can-produce-listt-but-cannot-response-oklistt-build
		return Response.ok().type(MediaType.APPLICATION_JSON).entity(result.toArray(new Canteen[result.size()])).build();
	}

}