package de.metafinanz.mam.backend.repository.json;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
public class JSONNewPassword {

	public String token;
	public String newPassword;
}
