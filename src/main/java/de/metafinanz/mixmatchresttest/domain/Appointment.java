package de.metafinanz.mixmatchresttest.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

import de.metafinanz.mixmatchresttest.domain.json.JSONAppointment;
import flexjson.JSONDeserializer;

@RooJavaBean
@RooToString
@RooEquals
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(entityName = "Appointment", finders = { "findAppointmentsByOwnerID",
		"findAppointmentsByAppointmentLocation" })
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

	/**
     */
	// TODO: Just save userID as reference?
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<UserE> participants = new HashSet<UserE>();

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
	/**
	 * Pushed in method to deserialize appointments which only contain ID
	 * references to the the owner and location. This is the default way to
	 * create an appointment via REST.<br>
	 * 
	 * There are two ways to represent an appointment in JSON: Either as a full
	 * object or only containing references to the ownerID and location. <br>
	 * For example: <br>
	 * 
	 * <pre>
	 * {
	 *  "appointmentDate":1383951600000,
	 *  "appointmentLocation":1,
	 *  "ownerID":1
	 * }
	 * 
	 * </pre>
	 * 
	 * The JSON can also be a full object:
	 * 
	 * <pre>
	 *  {
	 *   "appointmentDate" : 1384378395267,
	 *   "appointmentID" : 1,
	 *   "appointmentLocation" : {
	 *     "locationID" : 10,
	 *     "locationName" : "location 10",
	 *     "version" : 0
	 *   },
	 *   "ownerID" : {
	 *     "id" : 10,
	 *     "username" : "user 10",
	 *     "version" : 0
	 *   },
	 *   "participants" : [ {
	 *     "id" : 1,
	 *     "username" : "user 1",
	 *     "version" : 0
	 *   }, {
	 *     "id" : 11,
	 *     "username" : "testuser",
	 *     "version" : 0
	 *   } ],
	 *   "version" : 1
	 * }
	 * </pre>
	 * 
	 * @param json
	 *            JSON representation of the appointment.
	 * @return
	 */
	public static Appointment fromJsonToAppointment(String json) {
		// FIXME: Quick hack to convert json Appointments only containing IDs:
		Appointment newAppointment = new Appointment();
		try {
			newAppointment = new JSONDeserializer<Appointment>().use(null, Appointment.class).deserialize(json);
		} catch (ClassCastException e) {
			JSONAppointment aJsonAppointment = new JSONDeserializer<JSONAppointment>().use(null, JSONAppointment.class)
					.deserialize(json);
			if (aJsonAppointment.getOwnerID() == 0 || aJsonAppointment.getAppointmentLocation() == 0) {
				throw new ClassCastException();
			}
			newAppointment = new Appointment();
			newAppointment.setAppointmentID(aJsonAppointment.getAppointmentID());
			newAppointment.setAppointmentDate(aJsonAppointment.getAppointmentDate());
			newAppointment.setOwnerID(UserE.findUserE(aJsonAppointment.getOwnerID()));
			newAppointment.setAppointmentLocation(Location.findLocation(aJsonAppointment.getAppointmentLocation()));
		}
		return newAppointment;
	}
}
