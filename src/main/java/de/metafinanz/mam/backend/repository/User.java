package de.metafinanz.mam.backend.repository;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
@RooJpaActiveRecord(entityName = "User", finders = { "findUsersByUsernameLike",
		"findUsersByUsernameEquals" })
public class User {

	/**
     */
	@NotNull
	@Column(unique = true)
	private String username;

}
