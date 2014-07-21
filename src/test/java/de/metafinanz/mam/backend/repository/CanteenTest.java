package de.metafinanz.mam.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.metafinanz.mam.backend.controller.CanteenController;
import de.metafinanz.mam.backend.controller.impl.CanteenControllerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
public class CanteenTest {
	static Logger logger = LoggerFactory.getLogger(CanteenTest.class);
	
	@Test
	public void getCanteenByOffice() {
		
		List<Office> offices = Office.findAllOffices();
		logger.info("Have {} office/s.", offices.size());
		
		CanteenController controller = new CanteenControllerImpl();
		List<Canteen> canteens = controller.getLocationByOffice(offices.get(0).getId());
		
		assertNotNull(canteens);
		assertEquals("Expecting 10 canteens.", 10, canteens.size());
			
	}
	
	@Test
	public void getCanteensFarFarAwayByOffice() {

		Canteen aCanteen = new Canteen();
		aCanteen.setName("Canteen 2 far far away");
		aCanteen.setLatitude(8.1884351);
		aCanteen.setLongitude(1.6491052);
		aCanteen.persist();

		Office aOffice = new Office();
		aOffice.setName("Office Far Far Away");
		aOffice.setLatitude(8.1884351);
		aOffice.setLongitude(1.6491052);
		aOffice.persist();	
		
		
		Office office = Office.findOfficesByName("Office Far Far Away").getSingleResult();
		
		CanteenController controller = new CanteenControllerImpl();
		List<Canteen> canteens = controller.getLocationByOffice(office.getId());
		
		assertNotNull(canteens);
		assertEquals("Expecting 1 canteen.", 1, canteens.size());
		
		
	}

}
