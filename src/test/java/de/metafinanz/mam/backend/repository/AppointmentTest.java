package de.metafinanz.mam.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.metafinanz.mam.backend.controller.impl.AppointmentsControllerImpl;
import de.metafinanz.mam.backend.repository.json.JSONAppointment;

//@RunWith(JUnit4.class)
//@MockStaticEntityMethods
//@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
// @Transactional
// TODO: Cleanup
public class AppointmentTest {

	static Logger logger = LoggerFactory.getLogger(AppointmentTest.class);

	@Test
	public void testControllerAddParticipant() {
		System.out.println("All Appointments: "
				+ Appointment.findAllAppointments().toString());

		// Add non existent User to Appointment:
		Appointment anAppointment = Appointment.findAppointment(1L);
		System.out.println("\nBefore Adduser: " + anAppointment.toString());

		// New User only with username:
		User newTestParticipant = new User();
		newTestParticipant.setUsername("testControllerAddParticipant");
		newTestParticipant.setPassword("newTestPassword");
		newTestParticipant.setEnabled(true);
		newTestParticipant.persist();
				
		new AppointmentsControllerImpl().addParticipant(
				anAppointment.getAppointmentID(), newTestParticipant);

		anAppointment = Appointment.findAppointment(1L);
		System.out.println("After  Adduser: " + anAppointment.toString());

		Set<User> participants = anAppointment.getParticipants();
		org.junit.Assert.assertEquals(
				true,
				participants.contains(User.findUsersByUsernameEquals(
						"testControllerAddParticipant").getSingleResult()));

		System.out.println("Adding an existing user:");
		new AppointmentsControllerImpl().addParticipant(
				anAppointment.getAppointmentID(), User.findUser(5L));

		anAppointment = Appointment.findAppointment(1L);
		System.out.println(anAppointment.toString() + "\n");

		participants = anAppointment.getParticipants();
		org.junit.Assert.assertEquals(true,
				participants.contains(User.findUser(5L)));
	}

	@Ignore("Adding is possible. Scrambler will sort into groups")
	@Test
	public void testControllerAdd5Participants() {

		// Remove appointment 14 if already there
		Appointment anAppointment = Appointment.findAppointment(14L);
		if (anAppointment != null)
			anAppointment.remove();

		anAppointment = new Appointment();
		anAppointment.setAppointmentID(14L);
		anAppointment
				.setCanteen(Canteen.findAllCanteens().get(0));
		anAppointment.persist();

		System.out.println("\nBefore 4 Adduser: " + anAppointment.toString());
		// New User only with username:
		AppointmentsControllerImpl appointmentsControllerImpl = new AppointmentsControllerImpl();
		for (int i = 0; i < 4; i++) {
			String username = "newTestParticipant" + i;
			String password = "newTestPassword" + i;

			org.junit.Assert.assertNotNull(addNewUser(anAppointment,
					appointmentsControllerImpl, username, password));
		}

		System.out.println("After 4 Adduser: " + anAppointment.toString());

		// try to add a fifth participant
		org.junit.Assert.assertNull(addNewUser(anAppointment,
				appointmentsControllerImpl, "userNotToBeAdded", "aPassword"));

	}

	private Appointment addNewUser(Appointment anAppointment,
			AppointmentsControllerImpl appointmentsControllerImpl,
			String username, String password) {
		User newTestParticipant = new User();
		newTestParticipant.setUsername(username);
		newTestParticipant.setPassword(password);
		newTestParticipant.setEnabled(true);
		newTestParticipant.persist();
		return appointmentsControllerImpl.addParticipant(
				anAppointment.getAppointmentID(), newTestParticipant);
	}

	@Test
	public void testControllerRemoveParticipant() {
		// Add non existent User to Appointment:
		Appointment anAppointment = Appointment.findAppointment(1L);
		System.out.println("\nBefore Adduser: " + anAppointment.toString());

		// New User only with username:
		User newTestParticipant = new User();
		newTestParticipant.setUsername("newTestParticipant");
		newTestParticipant.setPassword("newTestPassword");
		newTestParticipant.setEnabled(true);
		newTestParticipant.persist();
		new AppointmentsControllerImpl().addParticipant(
				anAppointment.getAppointmentID(), newTestParticipant);

		anAppointment = Appointment.findAppointment(1L);
		System.out.println("After  Adduser: " + anAppointment.toString());

		Set<User> participants = anAppointment.getParticipants();
		org.junit.Assert.assertEquals(
				true,
				participants.contains(User.findUsersByUsernameEquals(
						"newTestParticipant").getSingleResult()));

		// Remove the user again:
		new AppointmentsControllerImpl().removeParticipant(anAppointment
				.getAppointmentID(),
				User.findUsersByUsernameEquals("newTestParticipant")
						.getSingleResult());

		anAppointment = Appointment.findAppointment(1L);
		System.out.println("After Remove: " + anAppointment.toString());

		participants = anAppointment.getParticipants();
		org.junit.Assert.assertEquals(
				false,
				participants.contains(User.findUsersByUsernameEquals(
						"newTestParticipant").getSingleResult()));

	}

	/**
	 * Test for the findAppointmentsByParticipant finder.
	 */
	@Test
	@Transactional
	public void testFindAppointmentsByParticipant() {
		logger.debug("Appointments for User with id 5 before:");
		List<Appointment> appointments = Appointment
				.findAppointmentsByParticipant(User.findUser(5L))
				.getResultList();

		Assert.assertEquals(appointments.size(), 0);

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, year + 1);
		Date appointmentDate = cal.getTime();

		Appointment firstAppointment = new Appointment();
		firstAppointment.setCanteen(Canteen.findCanteen(1L));
		firstAppointment.setAppointmentDate(appointmentDate);

		Set<User> participants = firstAppointment.getParticipants();
		participants.add(User.findUser(5L));
		// firstAppointment.setParticipants(participants);
		firstAppointment.persist();

		Appointment secondAppointment = new Appointment();
		secondAppointment.setCanteen(Canteen.findCanteen(1L));
		secondAppointment.setAppointmentDate(appointmentDate);

		participants = secondAppointment.getParticipants();
		participants.add(User.findUser(5L));
		// secondAppointment.setParticipants(participants);
		secondAppointment.persist();

		logger.debug("Appointments for User with id 5");
		appointments = Appointment.findAppointmentsByParticipant(
				User.findUser(5L)).getResultList();

		for (int i = 0; i < appointments.size(); i++) {
			System.out.println("Appointment " + i + " "
					+ appointments.get(i).toString());
		}

		Assert.assertEquals(appointments.size(), 2);

		System.out.println(Appointment.findAppointmentsByParticipant(
				User.findUser(1L)).getResultList());
	}
	
//	@Test
//	public void fromJsonToAppointmentTest() {
//		Date now = new Date();
//		String source = "{'appointmentDate':"+now.getTime()+",'appointmentID':1,'appointmentLocation':{'locationID':1},'participants':[{'id':1}],'version':1}";
//		
//
//		Appointment expectedApointment = new Appointment();
//		expectedApointment.setAppointmentDate(now);
//		expectedApointment.setAppointmentID(1L);
//		Location location = new Location();
//		location.setLocationID(1L);
//		expectedApointment.setCanteen(location);
//		Set<User> partici = new HashSet<User>();
//		User user = new User();
//		user.setUsername("user1");
//		user.setId(1L);
//		partici.add(user);
//		expectedApointment.setParticipants(partici);
//		expectedApointment.setVersion(1);
//		
//		Appointment result = Appointment.fromJsonToAppointment(source);
//		
//		assertEquals(expectedApointment, result);
//		
//	}
	
	@Ignore("Logged in User muss gemockt werden.")
	@Test 
	public void fromJsonAppointmentToAppointmentTest() {
		Date now = new Date();

		JSONAppointment appointment = new JSONAppointment();
		appointment.setAppointmentDate(now);
		appointment.setCanteen(1L);
		
		Appointment expectedApointment = new Appointment();
		expectedApointment.setAppointmentDate(now);
		Canteen location = new Canteen();
		location.setId(1L);
		location.setName("Canteen 1");
		location.setLatitude(48.1884351);
		location.setLongitude(11.6491052);
		location.setVersion(0);
		expectedApointment.setCanteen(location);
		Set<User> partici = new HashSet<User>();
		User user = User.findUser(5L);
		user.setId(5L);
		partici.add(user);
		expectedApointment.setParticipants(partici);
		
		Appointment result = Appointment.fromJsonAppointmentToAppointment(appointment);
		
		assertTrue(result.getParticipants().contains(user));
		assertEquals(expectedApointment.getCanteen(), result.getCanteen());
		assertEquals(expectedApointment, result);
		
	}
}
