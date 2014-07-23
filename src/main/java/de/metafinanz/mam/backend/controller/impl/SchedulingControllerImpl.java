package de.metafinanz.mam.backend.controller.impl;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import de.metafinanz.mam.backend.controller.AppointmentsController;
import de.metafinanz.mam.backend.controller.ScheduleingController;
import de.metafinanz.mam.backend.repository.Appointment;

@EnableScheduling
public class SchedulingControllerImpl implements ScheduleingController {
	

	static Logger logger = LoggerFactory.getLogger(SchedulingControllerImpl.class);

	@Override
	@Scheduled(cron="0 */5 7-20 * * MON-FRI")
	public void runManageAppointments() {
		logger.info("Starting scheduled job runManageAppointments() at {}", new Date());
		AppointmentsController appController = new AppointmentsControllerImpl();
		List<Appointment> result = searchForAppointmentsToScramble();
		Iterator<Appointment> i = result.iterator();
		while (i.hasNext()) {
			Appointment a = i.next();
			if (a.getRootAppointment() != null) {
				i.remove();
			}
		}
		logger.info("Found {} event(s). ", result.size());
		if (result.size() > 0) {
			logger.info("Preparing assignment.");
			for (Appointment app : result) {
				logger.info("preparing appointment: {}", app.toString());
				appController.assignGroupToParticipant(app.getAppointmentID());
			}
		} else {
			logger.info("Nothing to do.");
		}
	}

	private List<Appointment> searchForAppointmentsToScramble() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MINUTE, 5);
		Date start = cal.getTime();
		cal.add(Calendar.MINUTE, 12);
		Date end = cal.getTime();

		final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.GERMANY);
		
		logger.debug("Searching events starting between {} and {} ", df.format(start), df.format(end));
		boolean isAlreadyScrambled = false;
		List<Appointment> result = Appointment.findAppointmentsByScrambledAndAppointmentDateBetween(isAlreadyScrambled, start, end).getResultList();
		return result;
	}
	

//	@Scheduled(cron="* * * * * *") // every second
//	public void testScheduler() {
//		logger.info("Scheduler-Test: {}", new Date());
//	}
	


}
