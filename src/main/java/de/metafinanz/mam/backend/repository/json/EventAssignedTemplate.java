package de.metafinanz.mam.backend.repository.json;

import java.util.Date;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.User;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
public class EventAssignedTemplate extends MailTemplate {
	
	private Date eventDate;
	private int countParticipants;
	private String canteenName;
	private String address;
	private String city;
	private Integer postalCode;

	public EventAssignedTemplate(Appointment ap) {
		super();
		setTemplate("EventAssigned");
		String recipients = "";
		for (User user : ap.getParticipants()) {
			if (!recipients.equals("")) {
				recipients += ",";
			}
			recipients += user.getEMail();
		}
		setRecipients(recipients);
		
		this.eventDate = ap.getAppointmentDate();
		this.canteenName =  ap.getCanteen().getName();
		this.address = ap.getCanteen().getAddress();
		this.city = ap.getCanteen().getCity();
		this.postalCode = ap.getCanteen().getPostalCode();
		this.countParticipants = ap.getParticipants().size();
	}

}
