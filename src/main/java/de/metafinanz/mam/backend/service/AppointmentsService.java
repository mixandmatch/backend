package de.metafinanz.mam.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

@Component
@Path("/appointments")
public class AppointmentsService {

	@Autowired
	AppointmentsController appointmentsController;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Appointment> appointment() {
		return appointmentsController.getAppointments();
	}
	
	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response appointments_add(JSONAppointment appointment) {
		String result = appointmentsController.addAppointment(appointment);
		return Response.status(201).entity(result).build();
	}

}