package de.metafinanz.mam.backend.repository.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract public class AbstractCloud {

	static Logger logger = LoggerFactory.getLogger(AbstractCloud.class);

//	protected String SENDER_ID = "290811386777";
	protected String API_KEY = "apy_key";
	protected String API_USR = "user";
	protected String API_PAS = "pass";
	protected String URL_ACS = "https://api.cloud.appcelerator.com/v1/";

	public abstract String createSession() throws Exception ;

	abstract public String sendMessage(String datum, String text,
			String titel, String session_id) throws Exception;

	public String getAPI_KEY() {
		return API_KEY;
	}

	public void setAPI_KEY(String aPI_KEY) {
		API_KEY = aPI_KEY;
	}

	public String getAPI_USR() {
		return API_USR;
	}

	public void setAPI_USR(String aPI_USR) {
		API_USR = aPI_USR;
	}

	public String getAPI_PAS() {
		return API_PAS;
	}

	public void setAPI_PAS(String aPI_PAS) {
		API_PAS = aPI_PAS;
	}

	public String getURL_ACS() {
		return URL_ACS;
	}

	public void setURL_ACS(String uRL_ACS) {
		URL_ACS = uRL_ACS;
	}

	

	
}
