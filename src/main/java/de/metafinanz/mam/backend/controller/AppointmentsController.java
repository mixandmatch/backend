package de.metafinanz.mam.backend.controller;

import java.util.List;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

public interface AppointmentsController {

	List<Appointment> getAppointments();
	
	List<Appointment> getAppointmentsInFuture();
	
	List<Appointment> getAppointmentsForLocation(Long locationID);
	
	List<Appointment> getAppointmentsForUser(Long userID);
	
	// TODO: Return Appointment:
	String addAppointment(JSONAppointment appointment);

	/**
	 * Add a new participant to an appointment. The User has to either contain
	 * an ID or an username. (This allows it to process JSON Strings that
	 * contain either of those) If the user does not exist a new one is
	 * automatically created. The appointmentID has to exist.
	 * 
	 * @param appointmentID The id of the appointment.
	 * @param aUser The User object converted from the JSON received over REST.
	 * @return The Appointment if adding the participant was successful, null if
	 *         it failed.
	 */
	Appointment addParticipant(Long appointmentID, User aUser) throws IllegalArgumentException;

	Appointment removeParticipant(Long appointmentID, User aUser) throws IllegalArgumentException;

	// Only allowed for the owner?
	Appointment deleteAppointment();

}