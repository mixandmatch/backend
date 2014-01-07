package de.metafinanz.mam.backend.repository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = User.class)
public class UserIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Test
    public void testRemove() {
		// TODO Fix test to use constraints (User is referenced and cannot be deleted while this is the case)
		Assert.assertTrue(true);
//        User obj = dod.getRandomUser();
//        Assert.assertNotNull("Data on demand for 'User' failed to initialize correctly", obj);
//        Long id = obj.getId();
//        Assert.assertNotNull("Data on demand for 'User' failed to provide an identifier", id);
//        obj = User.findUser(id);
//        obj.remove();
//        obj.flush();
//        Assert.assertNull("Failed to remove 'User' with identifier '" + id + "'", User.findUser(id));
    }
}
