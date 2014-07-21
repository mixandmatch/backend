package de.metafinanz.mam.backend.repository.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract public class AbstractCloud implements ICloudServices {

	static Logger logger = LoggerFactory.getLogger(AbstractCloud.class);

	public static final String API_KEY = "Op2otECIKQW1KjmKu2DFWiClQxggEyEY";
	public static final String URL_ACS = "https://api.cloud.appcelerator.com/v1/"; 
	
	public static final String TEMPLATE = "Hallo, <br/><br/> bitte rufe diesen Link auf um dein Passwort zurückzusetzen: "
			+ "<br/><br/><br/>"
			+ "Link: <a href=\"$link$\">$link$</a><br/><br/>"
			+ "<br/>"
			+ "Hochachtungsvoll<br/>"
			+ "Dein Mix'n'Match-Team ";
	
}
