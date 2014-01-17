package de.metafinanz.mam.backend.repository;
import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

@RooIntegrationTest(entity = Appointment.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
public class AppointmentIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

}
