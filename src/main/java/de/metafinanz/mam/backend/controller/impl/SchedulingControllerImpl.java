package de.metafinanz.mam.backend.controller.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
	@Scheduled(cron="*/5 9-14 * * * MON-FRI")
	public void runManageAppointments() {
		logger.info("Starting scheduled job runManageAppointments() at {}", new Date());
		AppointmentsController appController = new AppointmentsControllerImpl();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MINUTE, 15);
		List<Appointment> result = Appointment.findAppointmentsByAppointmentDate(cal.getTime()).getResultList();
		for (Appointment app : result) {
			logger.info("preparing appointment: {}", app.toString());
			appController.assignGroupToParticipant(app.getAppointmentID());
		}
	}
	

//	@Scheduled(cron="* * * * * *") // every second
//	public void testScheduler() {
//		logger.info("Scheduler-Test: {}", new Date());
//	}
	


}
