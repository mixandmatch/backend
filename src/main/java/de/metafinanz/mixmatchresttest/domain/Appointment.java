package de.metafinanz.mixmatchresttest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import flexjson.JSONDeserializer;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
@RooJpaActiveRecord(entityName = "Appointment", finders = {
		"findAppointmentsByOwnerID", "findAppointmentsByAppointmentLocation" })
public class Appointment {

	/**
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private Long appointmentID;

	/**
     */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date appointmentDate;

	/**
     */
	@NotNull
	@ManyToOne
	private UserE ownerID;

	/**
     */
	@NotNull
	@ManyToOne
	private Location appointmentLocation;

	// @Transient
	// public Long getOwnerId() {
	// return this.ownerID.getId();
	// }
	//
	// @Transient
	// public void setOwnerId(Long ownerId) {
	// this.ownerID = UserE.findUserE(ownerId);
	// }
	//
	// @Transient
	// public Long getAppointmentLocation() {
	// return this.appointmentLocation.getLocationID();
	// }
	//
	// @Transient
	// public void setAppointmentLocation(Long locationID) {
	// this.appointmentLocation = Location.findLocation(locationID);
	// }

	public static Appointment fromJsonToAppointment(String json) {
		// FIXME: Quick hack to convert json Appointments only containing IDs:
		Appointment newAppointment = new Appointment();
		try {
			newAppointment = new JSONDeserializer<Appointment>().use(null,
					Appointment.class).deserialize(json);
		} catch (ClassCastException e) {
			JSONAppointment aJsonAppointment = new JSONDeserializer<JSONAppointment>()
					.use(null, JSONAppointment.class).deserialize(json);

			if (aJsonAppointment.getOwnerID() == 0
					|| aJsonAppointment.getAppointmentLocation() == 0) {
				throw new ClassCastException();
			}

			newAppointment = new Appointment();
			newAppointment
					.setAppointmentID(aJsonAppointment.getAppointmentID());
			newAppointment.setAppointmentDate(aJsonAppointment
					.getAppointmentDate());
			newAppointment.setOwnerID(UserE.findUserE(aJsonAppointment
					.getOwnerID()));
			newAppointment.setAppointmentLocation(Location
					.findLocation(aJsonAppointment.getAppointmentLocation()));
		}

		return newAppointment;
	}
}
