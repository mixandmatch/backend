package de.metafinanz.mam.backend.repository.util;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.metafinanz.mam.backend.repository.Appointment;
import de.metafinanz.mam.backend.repository.Canteen;
import de.metafinanz.mam.backend.repository.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
public class ScramblerTest {

	static Logger logger = LoggerFactory.getLogger(ScramblerTest.class);
	private Stack<User> stackParticipants;
	
	@Before
	public void setUp() {
    	stackParticipants = new Stack<User>();
    	for ( int i = 0; i < 10; i++) {
    		User user = new User();
    		user.setUsername("User-" + i);
			stackParticipants.add(user );
    	}
	}
	
    @Test
    public void testScamble() {
    	logger.error("testScamble");
    	
    	Appointment sourceAppointment = prepareAppointment();

		addUsers(sourceAppointment, 10);		

		List<Appointment> result = Scrambler.scrambleUsersOfAppointment(sourceAppointment);
		assertNotNull(result);
		assertEquals("Anzahl der Appointments entspricht nicht der Erwartung für 10 Teilnehmer.", 3, result.size());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testScambleWithNoParticipants() {
    	logger.error("testScambleWithNoParticipants");
    	
    	Appointment sourceAppointment = prepareAppointment();
		
		Scrambler.scrambleUsersOfAppointment(sourceAppointment);
    }
    
    @Test
    public void testScambleWithOneParticipants() {
    	logger.error("testScambleWithOneParticipants");
    	
    	Appointment sourceAppointment = prepareAppointment();

		addUsers(sourceAppointment, 1);
		
		List<Appointment> result = Scrambler.scrambleUsersOfAppointment(sourceAppointment);
		assertNotNull(result);
		assertEquals("Anzahl der Appointments entspricht nicht der Erwartung für 1 Teilnehmer.", 1, result.size());
    }

	private Appointment prepareAppointment() {
		Appointment sourceAppointment = new Appointment();
    	sourceAppointment.setAppointmentDate(new Date());
    	sourceAppointment.setAppointmentLocation(new Canteen());
		return sourceAppointment;
	}
    
    @Test
    public void testScambleWithFourParticipants() {
    	logger.error("testScambleWithFourParticipants");
    	
    	Appointment sourceAppointment = prepareAppointment();

		addUsers(sourceAppointment, 4);
		
		List<Appointment> result = Scrambler.scrambleUsersOfAppointment(sourceAppointment);
		assertNotNull(result);
		assertEquals("Anzahl der Appointments entspricht nicht der Erwartung für 4 Teilnehmer.", 1, result.size());
		assertEquals("Anzahl der Participants in der ersten Gruppe sollte 4 sein.", 4, result.get(0).getParticipants().size());
    }

	private void addUsers(Appointment sourceAppointment, int numberOfUsers) {
		for (int i = 0 ; i < numberOfUsers; i++) {
			sourceAppointment.getParticipants().add(stackParticipants.pop());
		}
	}
    
    @Test
    public void testScambleWithFiveParticipants() {
    	logger.error("testScambleWithFiveParticipants");
    	
    	Appointment sourceAppointment = prepareAppointment();

		addUsers(sourceAppointment, 5);

		List<Appointment> result = Scrambler.scrambleUsersOfAppointment(sourceAppointment);
		assertNotNull(result);
		assertEquals("Anzahl der Appointments entspricht nicht der Erwartung für 5 Teilnehmer.", 2, result.size());
		assertEquals("Anzahl der Participants in der ersten Gruppe sollte 3 sein.", 3, result.get(0).getParticipants().size());
		assertEquals("Anzahl der Participants in der zweiten Gruppe sollte 2 sein.", 2, result.get(1).getParticipants().size());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testScambleValidationNoAppointment() {
    	logger.error("testScambleValidationNoAppointment");
    			
		Scrambler.scrambleUsersOfAppointment(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testScambleValidationNoDate() {
    	logger.error("testScambleValidationNoDate");
    	
    	Appointment sourceAppointment = new Appointment();
    	sourceAppointment.setAppointmentLocation(new Canteen());
		addUsers(sourceAppointment, 1);
		
		Scrambler.scrambleUsersOfAppointment(sourceAppointment);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testScambleValidationNoLocation() {
    	logger.error("testScambleValidationNoLocation");
    	
    	Appointment sourceAppointment = new Appointment();
    	sourceAppointment.setAppointmentDate(new Date());
		addUsers(sourceAppointment, 1);
		
		Scrambler.scrambleUsersOfAppointment(sourceAppointment);
    }
    
}
