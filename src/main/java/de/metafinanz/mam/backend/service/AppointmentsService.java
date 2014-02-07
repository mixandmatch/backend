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

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

@Component
@Path("/appointments")
public class AppointmentsService {

	@Autowired
	AppointmentsController appointmentsController;

	static Logger logger = LoggerFactory.getLogger(AppointmentsService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Appointment> appointment() {
		return appointmentsController.getAppointments();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response appointments_add(JSONAppointment appointment) {
		logger.trace("entering appointments_add");
		String result = appointmentsController.addAppointment(appointment);
		return Response.status(201).entity(result).build();
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
	 * @return The new appointment. Null if there was an error.
	 */
	@POST
	@Path("{id}/addParticipant")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addParticipant(@PathParam("id") String id, User newParticipant) {
		logger.trace("entering addParticipant");
		logger.debug("Adding participant: " + newParticipant + " to appointment with id: " + id);
		Appointment result = appointmentsController.addParticipant(new Long(id), newParticipant);

		if (result == null) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} else {
			return Response.status(Status.CREATED).entity(result).build();
		}
	}

	@DELETE
	@Path("{id}/removeParticipant")
	@Produces(MediaType.APPLICATION_JSON)
	private Response removeParticipant(@PathParam("id") String id, User aParticipant) {
		logger.trace("entering removeParticipant");
		logger.debug("Removing participant: " + aParticipant + " from appointment with id: " + id);

		Appointment result = appointmentsController.removeParticipant(new Long(id), aParticipant);
		if (result == null) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} else {
			return Response.status(Status.CREATED).entity(result).build();
		}

	}

}