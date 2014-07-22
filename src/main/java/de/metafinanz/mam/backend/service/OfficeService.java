package de.metafinanz.mam.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
import de.metafinanz.mam.backend.repository.Location;
import de.metafinanz.mam.backend.repository.Office;

@Component
@Path("office")
public class OfficeService implements ILocationService<Office> {

	@Autowired
	OfficeController locationsController;

	static Logger logger = LoggerFactory.getLogger(OfficeService.class);

	/**
	 * Get all office locations.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response locations() {
		logger.trace("entering office locations()");
		List<Office> result = locationsController.getLocations();
		//http://stackoverflow.com/questions/6081546/jersey-can-produce-listt-but-cannot-response-oklistt-build
		return Response.ok().type(MediaType.APPLICATION_JSON).entity(result.toArray(new Office[result.size()])).build();
	}

	/**
	 * Gets a office location by it's id or 404 if the office could not be found. 
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getLocation(@PathParam("id") String id) {
		logger.trace("entering getLocation");
		logger.debug("find Location with id: {}", id);
		Office result = locationsController.getLocation(new Long(id));
		if (result != null) {
			return Response.ok().type(MediaType.APPLICATION_JSON).entity(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * This method allows to add a office location.
	 * EXAMPLE-JSON:<br/><br/>
	 * 
	 * <code>{"locationName":"Kantine 12","logitude":11.6491052,"latitude":48.1884351}</code>
	 * 
	 * @param aLocation
	 * @return Return HTTP code 201 if created successful or 409 if the location name already exists.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response locationAdd(Office aLocation) {
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
	 * Deletes a office location by it's id.
	 */
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