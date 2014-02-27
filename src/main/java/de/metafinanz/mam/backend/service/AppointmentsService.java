package de.metafinanz.mam.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

@Component
@Path("appointments")
public class AppointmentsService {

	@Autowired
	AppointmentsController appointmentsController;

	static Logger logger = LoggerFactory.getLogger(AppointmentsService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Appointment> appointments() {
		return appointmentsController.getAppointmentsInFuture();
	}

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response appointmentsForUser(@QueryParam("userID") String userID) {
		try {
			List<Appointment> result = appointmentsController.getAppointmentsForUser(new Long(
					userID));
			return Response.ok(result, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/location")
	@Produces(MediaType.APPLICATION_JSON)
	public Response appointmentsForLocation(@QueryParam("locationID") String locationID) {
		try {
			List<Appointment> result = appointmentsController.getAppointmentsForLocation(new Long(
					locationID));
			return Response.ok(result, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response appointmentsAdd(JSONAppointment appointment) {
		logger.trace("entering appointmentsAdd");
		try {
			Appointment result = appointmentsController.addAppointment(appointment);
			return Response.ok(result, MediaType.APPLICATION_JSON).status(Status.CREATED).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * Adds a new participant to an Appointment.
	 * 
	 * @param id
	 *            The appointment ID derived from the Path
	 * @param newParticipant
	 *            The new participant. As the client generally uses a JSON like
	 *            this: {"username":"testuser"}. The resulting User object does
	 *            not have an id value.
	 * @return The new appointment. Status.INTERNAL_SERVER_ERROR if there was an
	 *         error.
	 */
	@POST
	@Path("{id}/addParticipant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addParticipant(@PathParam("id") String id, User newParticipant) {
		logger.trace("entering addParticipant");

		try {
			logger.debug("Adding participant: " + newParticipant + " to appointment with id: " + id);
			Appointment result = appointmentsController
					.addParticipant(new Long(id), newParticipant);
			if (result != null) {
				return Response.ok(result, MediaType.APPLICATION_JSON).status(Status.CREATED)
						.build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	@POST
	@Path("{id}/removeParticipant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeParticipant(@PathParam("id") String id, User aParticipant) {
		logger.trace("entering removeParticipant");
		try {
			logger.debug("Removing participant: " + aParticipant + " from appointment with id: "
					+ id);

			Appointment result = appointmentsController.removeParticipant(new Long(id),
					aParticipant);
			if (result != null) {
				return Response.ok(result, MediaType.APPLICATION_JSON).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return Response.status(Status.INTERNAL_SERVER_ERROR).build();

	}

}