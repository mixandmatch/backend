package de.metafinanz.mam.backend.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import de.metafinanz.mam.backend.repository.util.TokenGenerator;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
@RooJpaActiveRecord (entityName = "Token", 
						finders = { "findTokensByTokenString" })
public class Token {
	
		
	private static final int DEFAULT_HOURS_FOR_VALID_UNTIL = 24;


	public Token() {
		super();
	}

	public Token(User user) {
		super();
		init(user);
	}


	private void init(User user) {
		tokenString = TokenGenerator.createToken();
		this.user = user;
		
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.HOUR, DEFAULT_HOURS_FOR_VALID_UNTIL);
		validUntil = cal.getTime();
		
		enabled = true;
	}


	static Logger logger = LoggerFactory.getLogger(User.class);

	/**
     */
	@NotNull
	@Column(unique = true)
	private String tokenString;
	
	@NotNull
	@ManyToOne
	private User user;
	

	/**
     */
	@NotNull
	@JsonIgnore
	private Boolean enabled;
	

	/**
     */
	@NotNull
	@JsonIgnore
	private Date validUntil;
	

	/**
     */
	@JsonIgnore
	private Date usageTime;

	public boolean isNotExpired() {
		Date now = new Date();
		
		return now.before(validUntil);
	}
}
