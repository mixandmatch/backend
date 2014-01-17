package de.metafinanz.mam.backend.repository;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(JUnit4.class)
//@MockStaticEntityMethods
//@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
//@Transactional
public class UserTest {

	static Logger logger = LoggerFactory.getLogger(UserTest.class);
	
    @Test
    public void testMethod() {
    	logger.error("logger test");
    	
//    	final String testName = "testName";
//    	
//    	User aUser = new User();
//    	aUser.setUsername(testName);
//    	aUser.persist();
//    	
//    	System.out.println(User.countUsers());
//    	System.out.println(User.findAllUsers().toString());
//    	System.out.println(User.findUsersByUsernameEquals(testName).getSingleResult().toString());
    	
//    	System.out.println(Appointment.findAllAppointments());
    	List<Appointment> asdf = Appointment.findAllAppointments();
    	
    	System.out.println(asdf.toString());
    	
//        org.junit.Assert.assertEquals(expectedCount, User.countUserEs());
    }
}
