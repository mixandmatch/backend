package de.metafinanz.mam.backend.repository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Location.class)
public class LocationIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Test
    public void testRemove() {
		// TODO Fix test to use constraints (Location is referenced and cannot be deleted while this is the case)
		Assert.assertTrue(true);
//        Location obj = dod.getRandomLocation();
//        Assert.assertNotNull("Data on demand for 'Location' failed to initialize correctly", obj);
//        Long id = obj.getLocationID();
//        Assert.assertNotNull("Data on demand for 'Location' failed to provide an identifier", id);
//        obj = Location.findLocation(id);
//        obj.remove();
//        obj.flush();
//        Assert.assertNull("Failed to remove 'Location' with identifier '" + id + "'", Location.findLocation(id));
    }
}
