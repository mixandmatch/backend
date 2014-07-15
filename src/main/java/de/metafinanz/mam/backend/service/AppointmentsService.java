package de.metafinanz.mam.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

@Component
@Path("event")
public class AppointmentsService extends BaseService {

	@Autowired
	AppointmentsController appointmentsController;

	static Logger logger = LoggerFactory.getLogger(AppointmentsService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Appointment> appointments() {
		return appointmentsController.getAppointmentsInFuture();
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppointment(@PathParam("id") Long id) {
		logger.trace("entering getAppointment");
		Appointment result = appointmentsController.getAppointment(id);
		if (result != null) {
			logger.info("Found appointment with id = {}.", id);
			return Response.ok().type(MediaType.APPLICATION_JSON).entity(result).build();
		} else {
			logger.warn("Appointment with id = {} not found.", id);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response appointmentsForUser(@QueryParam("userID") Long userID) {
		try {
			List<Appointment> result = appointmentsController.getAppointmentsForUser(userID);
			return Response.ok(result, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/byCanteen")
	@Produces(MediaType.APPLICATION_JSON)
	public Response appointmentsForLocation(@QueryParam("canteenId") Long canteenId) {
		try {
			List<Appointment> result = appointmentsController.getAppointmentsForCanteen(canteenId);
			return Response.ok(result, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Creates a new appointment for a given time and adds a location by an existing location ID.
	 * The current logged in user joins automatically.
	 * 
	 * EXAMPLE-JSON:<br/><br/>
	 *  
	 * <code>{"appointmentDate":"2014-12-01T12:00:00.000Z","appointmentLocation":1}</code>
	 * 
	 * @param appointment
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response appointmentsAdd(JSONAppointment appointment) {
		logger.trace("entering appointmentsAdd");
		User user = getCurrentUser();
		
		try {
			Appointment result = appointmentsController.addAppointment(appointment, user);
			return Response.status(Status.CREATED).entity(result).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * Current logged in user joins appointment
	 * <br/><br/>
	 * 
	 * @param id
	 *            The appointment ID derived from the Path
	 * 
	 * @return The new appointment. Status.INTERNAL_SERVER_ERROR if there was an
	 *         error.
	 */
	@POST
	@Path("{id}/join")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addParticipant(@PathParam("id") Long id) {
		logger.trace("entering addParticipant");
		User user = getCurrentUser();

		try {
			logger.debug("Adding participant '{}' to appointment with id={}", user.getUsername(), id);
			Appointment result = appointmentsController.addParticipant(id, user);
			if (result != null) {
				return Response.ok(result, MediaType.APPLICATION_JSON).status(Status.CREATED)
						.build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * Current logged in user leaves appointment.
	 * 
	 */
	@POST
	@Path("{id}/leave")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeParticipant(@PathParam("id") Long id) {
		logger.trace("entering removeParticipant");
		User user = getCurrentUser();
		try {
			logger.debug("Removing participant '{}' from appointment with id={}", user.getUsername() , id);

			Appointment result = appointmentsController.removeParticipant(id, user);
			if (result != null && result.getParticipants() != null && result.getParticipants().size() > 0) {
				return Response.ok().type(MediaType.APPLICATION_JSON).entity(result).build();
			} else {
				return Response.status(Status.GONE).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return Response.status(Status.INTERNAL_SERVER_ERROR).build();

	}

}