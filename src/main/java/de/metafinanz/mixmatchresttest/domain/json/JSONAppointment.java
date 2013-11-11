package de.metafinanz.mixmatchresttest.domain.json;

import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

import de.metafinanz.mixmatchresttest.domain.Appointment;

/**
 * Used for deserialization of an appointment only containing ID references to
 * the owner and location.
 * 
 * @see Appointment#fromJsonToAppointment(String)
 */
@RooJavaBean
@RooToString
@RooJson
public class JSONAppointment {

	private Long appointmentID;

	private Date appointmentDate;

	private Long ownerID;

	private Long appointmentLocation;
}
