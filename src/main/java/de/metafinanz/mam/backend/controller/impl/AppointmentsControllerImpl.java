package de.metafinanz.mam.backend.controller.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.controller.LocationsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

public class AppointmentsControllerImpl implements AppointmentsController {

	static Logger logger = LoggerFactory.getLogger(LocationsController.class);

	public List<Appointment> getAppointments() {
		return Appointment.findAllAppointments();
	}

	public String addAppointment(JSONAppointment appointment) {
		Appointment newAppointment = Appointment
				.fromJsonAppointmentToAppointment(appointment);
		newAppointment.persist();
		newAppointment.flush();
		return "appointment " + appointment.getAppointmentID() + " created";
	}

	@Override
	public String addParticipant(Long appointmentID, User newParticipant) {
		logger.trace("entering addParticipant");
		logger.debug("Adding participant " + newParticipant
				+ " to appointment with id: " + appointmentID);
		Appointment anAppointment = Appointment.findAppointment(appointmentID);
		logger.debug("Appointment from db: " + anAppointment);
		// TODO: findUsersByUsername like or equals?
		// TODO: Create new User if he does not exist
		User aUser = User.findUsersByUsernameLike(newParticipant.getUsername())
				.getSingleResult();
		Set<User> participants = anAppointment.getParticipants();
		participants.add(aUser);
		anAppointment.setParticipants(participants);
		anAppointment.persist();
		anAppointment.flush();
		return null;
	}

}