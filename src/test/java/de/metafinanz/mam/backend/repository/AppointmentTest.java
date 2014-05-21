package de.metafinanz.mam.backend.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.metafinanz.mam.backend.controller.impl.AppointmentsControllerImpl;

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
		newTestParticipant.setUsername("newTestParticipant");
		newTestParticipant.setPassword("newTestPassword");
		newTestParticipant.setEnabled(true);
		new AppointmentsControllerImpl().addParticipant(
				anAppointment.getAppointmentID(), newTestParticipant);

		anAppointment = Appointment.findAppointment(1L);
		System.out.println("After  Adduser: " + anAppointment.toString());

		Set<User> participants = anAppointment.getParticipants();
		org.junit.Assert.assertEquals(
				true,
				participants.contains(User.findUsersByUsernameEquals(
						"newTestParticipant").getSingleResult()));

		System.out.println("Adding an existing user:");
		new AppointmentsControllerImpl().addParticipant(
				anAppointment.getAppointmentID(), User.findUser(5L));

		anAppointment = Appointment.findAppointment(1L);
		System.out.println(anAppointment.toString() + "\n");

		participants = anAppointment.getParticipants();
		org.junit.Assert.assertEquals(true,
				participants.contains(User.findUser(5L)));
	}

	@Test
	public void testControllerAdd5Participants() {

		// Remove appointment 14 if already there
		Appointment anAppointment = Appointment.findAppointment(14L);
		if (anAppointment != null)
			anAppointment.remove();

		anAppointment = new Appointment();
		anAppointment.setAppointmentID(14L);
		anAppointment.setOwnerID(User.findAllUsers().get(0));
		anAppointment
				.setAppointmentLocation(Location.findAllLocations().get(0));
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
		firstAppointment.setOwnerID(User.findUser(1L));
		firstAppointment.setAppointmentLocation(Location.findLocation(1L));
		firstAppointment.setAppointmentDate(appointmentDate);

		Set<User> participants = firstAppointment.getParticipants();
		participants.add(User.findUser(5L));
		// firstAppointment.setParticipants(participants);
		firstAppointment.persist();

		Appointment secondAppointment = new Appointment();
		secondAppointment.setOwnerID(User.findUser(1L));
		secondAppointment.setAppointmentLocation(Location.findLocation(1L));
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

		System.out.println(Appointment.findAppointmentsByOwnerID(
				User.findUser(10L)).getResultList());

		System.out.println(Appointment.findAppointmentsByParticipant(
				User.findUser(1L)).getResultList());
	}
}
