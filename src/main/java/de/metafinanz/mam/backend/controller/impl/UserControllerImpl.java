package de.metafinanz.mam.backend.controller.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.metafinanz.mam.backend.controller.UserController;
import de.metafinanz.mam.backend.repository.Token;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.ResetPasswordTemplate;
import de.metafinanz.mam.backend.repository.util.ACS;

public class UserControllerImpl implements UserController {

	static Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

	@Override
	public User findUserByID(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public User getOrCreateUser(User aUser) {
		return User.getOrCreateUser(aUser);
	}
	
	@Override
	public User createUser(User aUser) {
		return User.createUser(aUser);
	}

	@Override
	public List<User> getUsers() {
		return User.findAllUsers();
	}

	@Override
	public List<User> searchUser(String username) {
		return User.findUsersByUsernameLike(username).getResultList();
	}

	@Override
	/**
	 * Generate new Password and send Email.
	 * @param user
	 */
	public void resetPwd(String username) throws IOException {
		User user = User.findUsersByUsernameEquals(username).getSingleResult();
		if (user == null) {
			throw new IllegalArgumentException("No user found.");
		} else {
			Token token = new Token(user);
			token.persist();
			ResetPasswordTemplate template = new ResetPasswordTemplate(token);
			ACS.getInstance().sendMail(template);
			logger.info("Sent email for password reset to {}.", user.getEMail());
		}
	}


	@Override
	/**
	 * 
	 * @param picture User's avatar
	 * @param aUser User-Object
	 */
	public User uploadPicture(byte[] picture, User aUser) {
		User user = User.findUser(aUser.getId());
		if (user != null) {
			user.setPicture(picture);
			user.merge();
			
			logger.debug("Picture of user {} got updated.", user.getId());
			return user;
		}
		logger.warn("User with id={} not found.", aUser.getId());
		throw new IllegalArgumentException("User not found.");
	}

	@Override
	public void setNewPassword(String token, String newPassword) {
		Token tokenObject = Token.findTokensByTokenString(token).getSingleResult();
		if (tokenObject.getEnabled() && tokenObject.isNotExpired()) {
			if (newPassword != null) {
				User user = User.findUser(tokenObject.getUser().getId());
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				
				user.setPassword(passwordEncoder.encode(newPassword));
				user.merge();
				tokenObject.setEnabled(false);
				tokenObject.setUsageTime(new Date());
				tokenObject.merge();
				logger.info("Password for user '{}' has been resetted.", user.getUsername());
			}
		}
	}

}