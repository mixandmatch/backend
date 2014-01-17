package de.metafinanz.mam.backend.controller.impl;

import java.util.List;

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

public class AppointmentsControllerImpl implements AppointmentsController {


	public List<Appointment> getAppointments() {
		return Appointment.findAllAppointments();
	}

	public String addAppointment(JSONAppointment appointment) {
		Appointment newAppointment = Appointment.fromJsonAppointmentToAppointment(appointment);
		newAppointment.persist();
		return "appointment " + appointment.getAppointmentID() + " created";
	}

}