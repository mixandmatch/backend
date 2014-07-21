package de.metafinanz.mam.backend.repository.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ACSTest {

	private ACS acs;

	@Before
	public void init() {
		acs = ACS.getInstance();
	}
	
	@Ignore
	@Test
	public void getSessionTest() throws Exception {
				
		String session = acs.createSession();
		
		assertTrue(session != null);
	}
	
	@Ignore
	@Test
	public void sendmessageTest() throws Exception {
				
		acs.send();
		
		assertTrue(true);
	}
}
