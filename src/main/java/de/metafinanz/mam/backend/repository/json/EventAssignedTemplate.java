package de.metafinanz.mam.backend.repository.json;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	static Logger logger = LoggerFactory.getLogger(EventAssignedTemplate.class);
	
	private Date eventDateObject;
	private String eventDate;
	private int countParticipants;
	private String canteenName;
	private String address;
	private String city;
	private Integer postalCode;

	private static final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.GERMANY);
	
	public EventAssignedTemplate(Appointment ap) {
		super();
		setTemplate("EventAssigned");
		String recipients = "";
		for (User user : ap.getParticipants()) {
			if (!recipients.equals("")) {
				recipients += ",";
			}
			recipients += user.getEMail();
			logger.debug("Added {} to the list of participants.", user.getEMail());
		}
		setRecipients(recipients);
		
		this.eventDateObject = ap.getAppointmentDate();
		this.eventDate = df.format(eventDateObject);
		this.canteenName =  ap.getCanteen().getName();
		this.address = ap.getCanteen().getAddress();
		this.city = ap.getCanteen().getCity();
		this.postalCode = ap.getCanteen().getPostalCode();
		this.countParticipants = ap.getParticipants().size();
		
		logger.debug("Created EventAssignedTemplate: {}", this.toString());
	}

}
