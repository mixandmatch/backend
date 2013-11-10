package de.metafinanz.mixmatchresttest.domain;

import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class JSONAppointment {

	private Long appointmentID;

	private Date appointmentDate;

	private Long ownerID;

	private Long appointmentLocation;

}
