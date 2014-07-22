package de.metafinanz.mam.backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.metafinanz.mam.backend.repository.User;

public class BaseService {

	/**
	 * Gets the currently logged in user.
	 * @return
	 */
	protected User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    
	    User user = User.findUsersByUsernameEquals(name).getSingleResult();
		return user;
	}
}
