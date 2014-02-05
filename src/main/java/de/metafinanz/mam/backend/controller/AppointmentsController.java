package de.metafinanz.mam.backend.controller;

import java.util.List;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

public interface AppointmentsController {

	List<Appointment> getAppointments();
	List<Appointment> getAppointmentsForLocation(Long locationID);
//	TODO: Return Appointment?:
	String addAppointment(JSONAppointment appointment);
	String addParticipant(Long appointmentID, String aUserName);
	Appointment removeParticipant(Long userID);
	
//	Only allowed for the owner?
	Appointment deleteAppointment();

}