package de.metafinanz.mam.backend.controller;

import java.util.List;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

public interface AppointmentsController {

	List<Appointment> getAppointments();
	String addAppointment(JSONAppointment appointment);
	String addParticipant(Long appointmentID, String aUserName);

}