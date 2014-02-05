package de.metafinanz.mam.backend.repository.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Location;
import de.metafinanz.mam.backend.repository.User;

/**
 * Creates some sample data during application context changes. Should only be
 * used with the hibernate.hbm2ddl.auto value in the persistence.xml
 * configuration.
 */
@Component
public class SampleDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = Logger.getLogger(SampleDataGenerator.class);

	@Override
	@Transactional
	public final void onApplicationEvent(final ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			// Only add the sample data when the database is empty
			if (User.countUsers() == 0 && Location.countLocations() == 0 && Appointment.countAppointments() == 0) {
				logger.info("Parent started: generating sample data");
				this.insertSampleData();
				logger.trace("Sample data generated: ");
			}
		}
		// else {
		// Child started, ignoring
		// }
	}

	/**
	 * Inserts sample data into the database.
	 */
	private void insertSampleData() {
		// Random.nextInt(n) is more efficient than Math.random() (Math.random()
		// uses Random.nextDouble() internally.)
		final Random rand = new Random();

		User aUser = null;
		Location aLocation = null;
		Appointment anAppointment = null;

		for (int i = 1; i < 11; i++) {
			aUser = new User();
			aUser.setUsername("user " + i);
			aUser.persist();

			aLocation = new Location();
			aLocation.setLocationName("location " + i);
			aLocation.persist();
		}

		// Add one sample appointment with the last user and location
		anAppointment = new Appointment();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, rand.nextInt(7) + 1);
		Date date = cal.getTime();
		anAppointment.setAppointmentDate(date);

		anAppointment.setAppointmentLocation(aLocation);
		anAppointment.setOwnerID(aUser);

		Set<User> participants = anAppointment.getParticipants();
		participants.add(User.findUser(1L));
		anAppointment.setParticipants(participants);
		
		anAppointment.persist();

	}

}