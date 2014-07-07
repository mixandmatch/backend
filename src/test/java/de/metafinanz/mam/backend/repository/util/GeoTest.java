package de.metafinanz.mam.backend.repository.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDistance() {
		double result = Geo.getDistance(32.9697, -96.80322, 29.46786, -98.53506, 'K');
		double expect = 422.73893139401383;
		double delta = 0.0;
		assertEquals("Entfernung anders als erwartet", expect, result, delta);
	}

}
