package de.metafinanz.mam.backend.repository.util;

import java.io.IOException;

import de.metafinanz.mam.backend.repository.json.MailTemplate;

public interface ICloudServices {

	public String sendMessage(String datum, String text, String titel, String session_id) throws Exception;
	
	public String sendMail(MailTemplate body) throws IOException;
	
}
