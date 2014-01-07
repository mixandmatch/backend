package de.metafinanz.mam.backend.repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(JUnit4.class)
//@MockStaticEntityMethods
//@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
public class UserTest {

    @Test
    public void testMethod() {
    	
    	User aUser = new User();
    	aUser.setUsername("testName");
    	aUser.persist();
    	
    	System.out.println(User.countUsers());
    	System.out.println(User.findAllUsers().toString());
    	
//        org.junit.Assert.assertEquals(expectedCount, User.countUserEs());
    }
}
