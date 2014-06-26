package de.metafinanz.mam.backend.repository;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

@RooIntegrationTest(entity = Office.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml") 
public class OfficeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
