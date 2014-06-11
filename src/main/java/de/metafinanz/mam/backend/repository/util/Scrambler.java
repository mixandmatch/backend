package de.metafinanz.mam.backend.repository.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;

@Component
public class Scrambler {
	
	public static final int MAX_GROUPSIZE = 4;
	public static final int MIN_GROUPSIZE = 2;
	
	/**
	 * This method receives one appointment with all the people which are registered to this appointment.
	 * It splits the users into small groups and random order and return a list with appointments for each group. 
	 * @return list with appointment element for each group
	 */
	public static List<Appointment> scrambleUsersOfAppointment(Appointment sourceAppointment) {

		if (isValid(sourceAppointment)) {
			return scamble(sourceAppointment);
		} else {
			throw new IllegalArgumentException("appointment can not be null or empty and has to have at least one user.");
		}
	}

	/**
	 * We trust that the order within a Set is random enough. Further improvements are possible.
	 * @param sourceAppointment
	 * @return list of appointments
	 */
	private static List<Appointment> scamble(Appointment sourceAppointment) {
		List<Appointment> listResult = new ArrayList<Appointment>();
		
		Stack<User> participantStack = new Stack<User>();
		participantStack.addAll(sourceAppointment.getParticipants());
		
		Appointment newAppointment = null;
		while (participantStack.size() > 0) {
			if (participantStack.size() == MIN_GROUPSIZE 
					&& newAppointment != null 
					&& newAppointment.getParticipants().size() > (MAX_GROUPSIZE - MIN_GROUPSIZE) ) { //last group of two
				if (newAppointment != null) {
					listResult.add(newAppointment);
					newAppointment = null;
				}
			}
			
			User participant = participantStack.pop();
			if (newAppointment == null) {
				newAppointment =  new Appointment();
				newAppointment.setAppointmentDate(sourceAppointment.getAppointmentDate());
				newAppointment.setAppointmentLocation(sourceAppointment.getAppointmentLocation());
			}
			
			newAppointment.getParticipants().add(participant);
			if (newAppointment.getParticipants().size() == 1) {
				newAppointment.setOwnerID(participant);
			}
			if (participantStack.size() == 1 && newAppointment.getParticipants().size() == 1) { //last group of two
				newAppointment.getParticipants().add(participantStack.pop());
			}
			
			if (newAppointment.getParticipants().size() == MAX_GROUPSIZE || participantStack.empty()) { 
				listResult.add(newAppointment);
				newAppointment = null;
			}
		}
		
		return listResult;
	}

	private static boolean isValid(Appointment sourceAppointment) {
		if (sourceAppointment == null) {
			return false;
		} else if(sourceAppointment.getParticipants() == null) {
			return false;
		} else if (sourceAppointment.getParticipants().isEmpty()) {
			return false;
		} else if (sourceAppointment.getAppointmentDate() == null) {
			return false;
		} else if (sourceAppointment.getAppointmentLocation() == null) {
			return false;
		}
		return true;
	}

}
