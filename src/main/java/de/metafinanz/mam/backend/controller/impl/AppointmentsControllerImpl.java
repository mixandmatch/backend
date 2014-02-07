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
		Appointment newAppointment = Appointment.fromJsonAppointmentToAppointment(appointment);
		newAppointment.persist();
		return "appointment " + appointment.getAppointmentID() + " created";
	}

	/**
	 * Ads a new participant to the appointment. Automatically persists the
	 * changed appointment.
	 * 
	 * @param aUserName
	 *            Username of the participant to add.
	 */
	public Appointment addParticipant(Long appointmentID, User aUser)
			throws IllegalArgumentException {
		logger.trace("entering addParticipant");
		logger.debug("Adding participant with ID: " + aUser + " to appointment with ID: "
				+ appointmentID);

		aUser = User.getOrCreateUser(aUser);

		Appointment anAppointment = Appointment.findAppointment(appointmentID);
		logger.debug("Appointment from db: " + anAppointment);
		anAppointment.getParticipants().add(aUser);
		anAppointment.merge();
		return anAppointment;
	}

	@Override
	public Appointment removeParticipant(Long appointmentID, User aUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsForLocation(Long locationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment deleteAppointment() {
		// TODO Auto-generated method stub
		return null;
	}

}