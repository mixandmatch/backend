package de.metafinanz.mam.backend.repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.aspectj.weaver.AjAttribute;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import de.metafinanz.mam.backend.repository.json.JSONAppointment;
import flexjson.JSONDeserializer;


/**
 * Use case background of this POJO: 
 * 
 * The first type of appointment will be created by the first participant and can be seen as 'request'. Participants 
 * can add themselves or remove themselves. This kind of appointment has no rootAppointment and will be deleted when 
 * the last participant removes himself.
 * 
 * The second type will be created by the system when it's time to build up small groups for the appointment. This type 
 * has a root appointment. Participants can not add or remove themselves from this type because it is fixed.  
 *  
 * @author tsp
 *
 */
@RooJavaBean
@RooToString
@RooEquals
@RooJson(deepSerialize = true)
@RooJpaActiveRecord(entityName = "Appointment", 
		finders = { "findAppointmentsByAppointmentLocation", "findAppointmentsByAppointmentDateGreaterThan" })
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
	 * Value is NULL if this appointment is the root with all participants in one set.
	 * If this value is not null then the value points to the appointment of the root appointment. This means
	 * that this appointment was generated and contains the participants that build up a real group with a 
	 * predefined maximum of participants.
     */	
	@ManyToOne
	private Appointment rootAppointment; 

	/**
     */
	@NotNull
	@ManyToOne
	private Location appointmentLocation;

	/**
     */
	// TODO: Just save userID as reference?
	// fetch = Eager fixes the lazy init error when the participants are
	// accessed after the session is closed.
	// TODO: Change cascade to: cascade={REMOVE, REFRESH, DETACH}?
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = User.class)
	private Set<User> participants = new HashSet<User>();

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
	 * references to the the first participant and location. This is the default way to
	 * create an appointment via REST.<br>
	 * 
	 * There are two ways to represent an appointment in JSON: Either as a full
	 * object or only containing references to the first participant and location. <br>
	 * For example: <br>
	 * 
	 * <pre>
	 * {
	 *  "appointmentDate":1383951600000,
	 *  "appointmentLocation":1,
	 *  "participants" : [ {
	 *     "id" : 1,
	 *     "username" : "user 1",
	 *     "version" : 0
	 *   }
	 * }
	 * 
	 * </pre>
	 * 
	 * The JSON can also be a full object:
	 * 
	 * <pre>
	 *  {
	 *   "appointmentDate" : 1384378395267,
	 *   "rootAppointment" : 0
	 *   "appointmentID" : 1,
	 *   "appointmentLocation" : {
	 *     "locationID" : 10,
	 *     "locationName" : "location 10",
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
			newAppointment = new JSONDeserializer<Appointment>().use(null, Appointment.class)
					.deserialize(json);
		} catch (ClassCastException e) {
			JSONAppointment aJsonAppointment = new JSONDeserializer<JSONAppointment>().use(null,
					JSONAppointment.class).deserialize(json);
			if (aJsonAppointment.getAppointmentLocation() == 0) {
				throw new ClassCastException();
			}
			newAppointment = new Appointment();
			newAppointment.setAppointmentID(aJsonAppointment.getAppointmentID());
			newAppointment.setAppointmentDate(aJsonAppointment.getAppointmentDate());
			newAppointment.setRootAppointment(Appointment.findAppointment(aJsonAppointment.getRootAppointment()));
			newAppointment.setAppointmentLocation(Location.findLocation(aJsonAppointment
					.getAppointmentLocation()));
		}
		return newAppointment;
	}

	public static Appointment fromJsonAppointmentToAppointment(JSONAppointment aJSONAppointment) {
		// FIXME: Quick hack to convert json Appointments only containing IDs:
		Appointment newAppointment = new Appointment();
		// newAppointment.setAppointmentID(aJSONAppointment.getAppointmentID());
		newAppointment.setAppointmentDate(aJSONAppointment.getAppointmentDate());
		newAppointment.setRootAppointment(Appointment.findAppointment(aJSONAppointment.getRootAppointment()));
		newAppointment.setAppointmentLocation(Location.findLocation(aJSONAppointment
				.getAppointmentLocation()));
		return newAppointment;
	}

	public static TypedQuery<Appointment> findAppointmentsByParticipant(User aParticipant) {
		if (aParticipant == null)
			throw new IllegalArgumentException("The aParticipant argument is required");
		EntityManager em = Appointment.entityManager();
		// TypedQuery<Appointment> q = em.createQuery(
		// "SELECT o FROM Appointment AS o WHERE o.PARTICIPANTS = :aParticipant",
		// Appointment.class);
		TypedQuery<Appointment> q = em
				.createQuery(
						"SELECT DISTINCT o FROM Appointment o JOIN o.participants p where p.id = :aParticipant AND o.rootAppointment IS NULL",
						Appointment.class);
		q.setParameter("aParticipant", aParticipant.getId());
		System.out.println(q.toString());
		return q;

	}

	public static TypedQuery<Appointment> findAppointmentsByAppointmentLocation(
			Location appointmentLocation) {
		if (appointmentLocation == null)
			throw new IllegalArgumentException("The appointmentLocation argument is required");
		EntityManager em = Appointment.entityManager();
		TypedQuery<Appointment> q = em
				.createQuery(
						"SELECT o FROM Appointment AS o WHERE o.appointmentLocation = :appointmentLocation AND o.appointmentDate > :appointmentDate AND o.rootAppointment IS NULL",
						Appointment.class);
		q.setParameter("appointmentLocation", appointmentLocation);
		q.setParameter("appointmentDate", new Date());
		return q;
	}

	public static Appointment findAppointmentsByAppointmentId(Long appointmentId) {
        if (appointmentId == null) return null;
        return entityManager().find(Appointment.class, appointmentId);
	}
}
