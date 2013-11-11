package de.metafinanz.mixmatchresttest.web;

import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.metafinanz.mixmatchresttest.domain.Appointment;
import de.metafinanz.mixmatchresttest.domain.UserE;

@RequestMapping("/appointments")
@Controller
@RooWebScaffold(path = "appointments", formBackingObject = Appointment.class)
@RooWebJson(jsonObject = Appointment.class)
@RooWebFinder
public class AppointmentController {

	private static final Logger logger = Logger.getLogger(AppointmentController.class);

	/**
	 * Adds a user as participant to the given appointment. If the user does not
	 * exist its created.
	 * 
	 * @param JSON
	 *            representation of a User. For example: {"username":"testuser"}
	 * @return HTTP Response: 200 if everything went well, 400 if something went
	 *         wrong.
	 */
	@RequestMapping(value = "/{appointmentID}/addParticipant", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addParticipant(@PathVariable("appointmentID") Long appointmentID,
			@RequestBody String json) {
		HttpStatus status = HttpStatus.CREATED;
		
		logger.trace("Entering addParticipant");
		UserE newParticipant = UserE.fromJsonToUserE(json);
		logger.trace("Received participant " + newParticipant.toString());

		if (newParticipant.getUsername() != null) {
			TypedQuery<UserE> userResult = UserE.findUserEsByUsernameEquals(newParticipant.getUsername());

			if (userResult.getResultList().size() == 0) {
				logger.trace("Participant not found in database, creating new one");
				newParticipant.persist();
				// Re-read user from database, now with id
				newParticipant = UserE.findUserEsByUsernameEquals(newParticipant.getUsername()).getSingleResult();
			} else {
				newParticipant = userResult.getSingleResult();
				logger.trace("Participant found in database: " + newParticipant.toString());
			}

			Appointment anAppointment = Appointment.findAppointment(appointmentID);

			if (anAppointment != null) {
				logger.trace("Adding new participant " + newParticipant.toString() + " to appointment "
						+ anAppointment.toString());

				Set<UserE> participants = anAppointment.getParticipants();
				participants.add(newParticipant);
				anAppointment.setParticipants(participants);

				logger.trace("Added participant: " + anAppointment.toString());
				anAppointment.persist();
			} else {
				logger.warn("Appointment not found in Database, Aborting");
				status = HttpStatus.BAD_REQUEST;
			}
		} else {
			logger.warn("No username found, Aborting");
			status = HttpStatus.BAD_REQUEST;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<String>(headers, status);
	}
}
