package de.metafinanz.mam.backend.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Canteen;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;
import de.metafinanz.mam.backend.repository.util.Scrambler;

public class AppointmentsControllerImpl implements AppointmentsController {

	static Logger logger = LoggerFactory.getLogger(AppointmentsControllerImpl.class);

	public List<Appointment> getAppointments() {
		return Appointment.findAllAppointments();
	}

	public List<Appointment> getAppointmentsInFuture() {
		return Appointment.findAppointmentsByAppointmentDateGreaterThan(
				new Date()).getResultList();
	}
	
	public Appointment getAppointment(Long id) {
		logger.trace("entering getAppointment");
		Appointment app = Appointment.findAppointment(id);
		logger.trace("return find Appointment result: {}", app);
		return app;
	}

	public Appointment addAppointment(JSONAppointment appointment) {
		Appointment newAppointment = Appointment
				.fromJsonAppointmentToAppointment(appointment);
		Set<User> setParticipants = newAppointment.getParticipants();
		newAppointment.setParticipants(null); 
		newAppointment.persist();
		
		for (User p : setParticipants) {
			addParticipant(newAppointment.getAppointmentID(), p);
		}

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
		logger.debug("Adding participant with ID: " + aUser
				+ " to appointment with ID: " + appointmentID);

		aUser = getUserFromDB(aUser);

		Appointment anAppointment = getFutureAppointment(appointmentID);
		
		Set<User> participants = anAppointment.getParticipants();

		participants.add(aUser);
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
		logger.debug("Removing participant with ID: {} from appointment with ID: {}", aUser, appointmentID);

		aUser = getUserFromDB(aUser);

		Appointment anAppointment = getFutureAppointment(appointmentID);

		// Possible:?
		// anAppointment.getParticipants().remove(aUser);

		Set<User> participants = anAppointment.getParticipants();

		for (Iterator<User> iterator = participants.iterator(); iterator
				.hasNext();) {
			User user = (User) iterator.next();
			if (user.getId() == aUser.getId()) {
				participants.remove(user);
				break;
			}
		}
		anAppointment.merge();
		return anAppointment;
	}

	private Appointment getFutureAppointment(Long appointmentID) {
		Appointment anAppointment = Appointment.findAppointment(appointmentID);
		if (anAppointment == null) {
			logger.debug("No appointment found");
			return null;
		}
		
		if (anAppointment.getAppointmentDate() != null && new Date().after(anAppointment.getAppointmentDate())) {
			String msg = "Appointments in the past can not be edited.";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
		
		logger.debug("Appointment from db: {}", anAppointment);
		return anAppointment;
	}

	private User getUserFromDB(User aUser) {
		if (aUser == null || aUser.getId() == null) {
			String msg = "User object not valid or has no user id.";
			logger.warn(msg);
			throw new IllegalArgumentException(msg);
		}
		aUser = User.findUser(aUser.getId());
		
		if (aUser == null) {
			String msg = "No user found for given user ID.";
			logger.warn(msg);
			throw new IllegalArgumentException(msg);
		}
		return aUser;
	}

	@Override
	public List<Appointment> getAppointmentsForCanteen(Long canteenId) {
		Canteen aLocation = Canteen.findCanteen(canteenId);
		return Appointment.findAppointmentsByCanteen(aLocation)
				.getResultList();
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

		List<Appointment> appointmentsByParticipant = Appointment
				.findAppointmentsByParticipant(aUser).getResultList();

		if (appointmentsByParticipant != null) {
			result.addAll(appointmentsByParticipant);
		}

		return result;

	}

	@Override
	public List<Appointment> assignGroupToParticipant(Long appointmentID)
			throws IllegalArgumentException {
		logger.trace("entering assignGroupToParticipant");
		logger.debug("Assigning participants to groups for appointment with ID: {}", appointmentID);

		Appointment anAppointment = Appointment.findAppointment(appointmentID);
		logger.debug("Appointment from db: {}", anAppointment);
		List<Appointment> result = Scrambler.scrambleUsersOfAppointment(anAppointment);
		
		for (Appointment ap : result) {
			ap.persist();
		}
		// called from service?
		// persist here? 
		// send notifications here?
		return result;
	}

}