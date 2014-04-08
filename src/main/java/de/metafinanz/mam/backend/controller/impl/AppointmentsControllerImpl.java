package de.metafinanz.mam.backend.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.controller.LocationsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Location;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

public class AppointmentsControllerImpl implements AppointmentsController {

	static Logger logger = LoggerFactory.getLogger(LocationsController.class);

	public List<Appointment> getAppointments() {
		return Appointment.findAllAppointments();
	}
	
	public List<Appointment> getAppointmentsInFuture() {
		return Appointment.findAppointmentsByAppointmentDateGreaterThan(new Date()).getResultList();
	}

	public Appointment addAppointment(JSONAppointment appointment) {
		Appointment newAppointment = Appointment.fromJsonAppointmentToAppointment(appointment);
		newAppointment.persist();
		return newAppointment;
	}

	/**
	 * Ads a new participant to the appointment. Automatically persists the
	 * changed appointment.
	 * 
	 * @param aUserName
	 *            Username of the participant to add.
	 */
	@Override
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

	/**
	 * Removes a participant from the appointment. Automatically persists the
	 * changed appointment.
	 * 
	 * @param aUserName
	 *            Username of the participant to remove.
	 */
	@Override
	public Appointment removeParticipant(Long appointmentID, User aUser)
			throws IllegalArgumentException {
		logger.trace("entering removeParticipant");
		logger.debug("Removing participant with ID: " + aUser + " from appointment with ID: "
				+ appointmentID);

		aUser = User.getOrCreateUser(aUser);

		Appointment anAppointment = Appointment.findAppointment(appointmentID);
		logger.debug("Appointment from db: " + anAppointment);

		// Possible:?
		// anAppointment.getParticipants().remove(aUser);

		Set<User> participants = anAppointment.getParticipants();

		for (Iterator<User> iterator = participants.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (user.getId() == aUser.getId()) {
				participants.remove(user);
				break;
			}
		}
		anAppointment.merge();
		return anAppointment;
	}

	@Override
	public List<Appointment> getAppointmentsForLocation(Long locationID) {
		Location aLocation = Location.findLocation(locationID);
		return Appointment.findAppointmentsByAppointmentLocation(aLocation).getResultList();
	}

	@Override
	public Appointment deleteAppointment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsForUser(Long userID) {
		List<Appointment> result = new ArrayList<Appointment>();
		
		User aUser = User.findUser(userID);
		
		List<Appointment> appointmentsByOwner = Appointment.findAppointmentsByOwnerID(aUser).getResultList();
		List<Appointment> appointmentsByParticipant = Appointment.findAppointmentsByParticipant(aUser).getResultList();
		
		if(appointmentsByOwner != null){
			result.addAll(appointmentsByOwner);
		}
		
		if(appointmentsByParticipant != null){
			result.addAll(appointmentsByParticipant);
		}
		
		return result;
		
	}

	

}