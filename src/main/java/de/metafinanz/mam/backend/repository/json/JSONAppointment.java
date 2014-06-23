package de.metafinanz.mam.backend.repository.json;

import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import de.metafinanz.mam.backend.repository.Appointment;


/**
 * Used for deserialization of an appointment only containing ID references to
 * the the first participant and location.
 * 
 * @see Appointment#fromJsonToAppointment(String)
 */
@RooJavaBean
@RooToString
@RooJson
public class JSONAppointment {

	private Long appointmentID;

	private Date appointmentDate;

	private Long rootAppointment;
	
	private Long appointmentLocation;
	
	private Long participant;
}
