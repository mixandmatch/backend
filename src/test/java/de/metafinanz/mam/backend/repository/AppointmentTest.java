package de.metafinanz.mam.backend.repository;

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
public class AppointmentTest {

	static Logger logger = LoggerFactory.getLogger(AppointmentTest.class);

	@Test
	public void testMethod() {
		// logger.error("logger test");

		List<Appointment> asdf = Appointment.findAllAppointments();
		System.out.println("All Appointments: " + asdf.toString());

		// Add non existant User to Appointment
		Appointment anAppointment = Appointment.findAppointment(new Long(1));
		System.out.println("\nBefore Adduser: " + anAppointment.toString());
		new AppointmentsControllerImpl().addParticipant(
				anAppointment.getAppointmentID(), "newTestParticipant");

		anAppointment = Appointment.findAppointment(new Long(1));
		System.out.println("After  Adduser: " + anAppointment.toString());

		System.out.println("Adding an existing user:");
		new AppointmentsControllerImpl().addParticipant(anAppointment
				.getAppointmentID(), User.findUser(new Long(5)).getUsername());

		anAppointment = Appointment.findAppointment(new Long(1));
		System.out.println(anAppointment.toString() + "\n");

		asdf = Appointment.findAppointmentsByOwnerID(
				User.findUser(new Long(10))).getResultList();
		System.out.println(asdf.toString());

		asdf = Appointment.findAppointmentsByParticipant(
				User.findUser(new Long(1))).getResultList();
		System.out.println(asdf.toString());

		// System.out.println(User.findUser(new
		// Long(1)).getAppointments().toString());

		// org.junit.Assert.assertEquals(expectedCount, User.countUserEs());
	}

	/**
	 * Test for the findAppointmentsByParticipant finder.
	 */
	@Test
	@Transactional
	public void testFindAppointmentsByParticipant() {
		logger.debug("Appointments for User with id 5 before:");
		List<Appointment> appointments = Appointment.findAppointmentsByParticipant(User.findUser(5L)).getResultList();
		
		Assert.assertEquals(appointments.size(), 0);
		
		
		Appointment firstAppointment = new Appointment();
		firstAppointment.setOwnerID(User.findUser(1L));
		firstAppointment.setAppointmentLocation(Location.findLocation(1L));
		
		Set<User> participants = firstAppointment.getParticipants();
		participants.add(User.findUser(5L));
//		firstAppointment.setParticipants(participants);
		firstAppointment.persist();
		
		
		Appointment secondAppointment = new Appointment();
		secondAppointment.setOwnerID(User.findUser(1L));
		secondAppointment.setAppointmentLocation(Location.findLocation(1L));
		
		participants = secondAppointment.getParticipants();
		participants.add(User.findUser(5L));
//		secondAppointment.setParticipants(participants);
		secondAppointment.persist();
		
		logger.debug("Appointments for User with id 5");
		appointments = Appointment.findAppointmentsByParticipant(User.findUser(5L)).getResultList();
		
		for (int i = 0; i < appointments.size(); i++) {
			System.out.println("Appointment " + i + " " + appointments.get(i).toString());
		}
		
		Assert.assertEquals(appointments.size(), 2);

	}
}
