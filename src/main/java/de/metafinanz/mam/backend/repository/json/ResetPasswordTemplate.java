package de.metafinanz.mam.backend.repository.json;


import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import de.metafinanz.mam.backend.repository.Token;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
public class ResetPasswordTemplate extends MailTemplate {
	
	private String token;

	public ResetPasswordTemplate(Token token) {
		super();
		setTemplate("ResetPWD");
		setRecipients(token.getUser().getEMail());
		this.token = token.getTokenString();
	}


}
