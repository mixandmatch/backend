package de.metafinanz.mam.backend.repository.json;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
public class MailTemplate {
	
	/**
	 * Example-JSON:
	 * 
	 * {"action":"email_from_template","controller":"custom_mailer","key":"Op2otECIKQW1KjmKu2DFWiClQxggEyEY","version":"v1","format":"json",
	 * "template":"ResetPWD","recipients":"tsp@metafinanz.de"}
	 */
	
	private String action;
	private String crontroller;
	private String key;
	private String version = "v1";
	private String format = "json";
	private String template;
	private String recipients;
		
	public MailTemplate() {
		super();
	}
	
	public MailTemplate(String action, String crontroller, String key, String template, String recipients) {
		super();
		this.action = action;
		this.crontroller = crontroller;
		this.key = key;
		this.template = template;
		this.recipients = recipients;
	}


}
