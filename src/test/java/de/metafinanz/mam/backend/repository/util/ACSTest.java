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
//		acs.setAPI_USR("VkqmqNRXbjTeXXzP8DQhTiiSTNFE20PH");
//		acs.setAPI_PAS("HhIXVoaTnqMCr4Qi25GfLJivWSawTCbc");
		acs.setAPI_USR("tobias.schnupp@metafinanz.de");
		acs.setAPI_PAS("1qayxsw2");
		acs.setAPI_KEY("VkqmqNRXbjTeXXzP8DQhTiiSTNFE20PH");
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
