package de.metafinanz.mam.backend.controller.impl;

import java.util.List;

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
		return "appointment " + appointment.getAppointmentID() + " created";
	}

	/**
	 * Ads a new participant to the appointment. Automatically persists the
	 * changed appointment. If the user does not exist a new one is
	 * automatically created.
	 * 
	 * @param aUserName
	 *            Username of the participant to add.
	 */
	@Override
	public String addParticipant(Long appointmentID, String aUserName) {
		logger.trace("entering addParticipant");
		logger.debug("Adding participant " + aUserName
				+ " to appointment with id: " + appointmentID);
		User participant;

		List<User> userResult = User.findUsersByUsernameEquals(aUserName)
				.getResultList();
		if (userResult.isEmpty()) {
			// User does not exist: Create it
			participant = new User();
			participant.setUsername(aUserName);
			participant.persist();
		} else {
			participant = userResult.get(0);
		}

		Appointment anAppointment = Appointment.findAppointment(appointmentID);
		logger.debug("Appointment from db: " + anAppointment);
		anAppointment.getParticipants().add(participant);
		anAppointment.merge();
		return null;
	}

	@Override
	public Appointment removeParticipant(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

}