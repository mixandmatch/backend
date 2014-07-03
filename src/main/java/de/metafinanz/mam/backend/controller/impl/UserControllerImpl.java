package de.metafinanz.mam.backend.controller.impl;

import java.util.List;

import de.metafinanz.mam.backend.controller.UserController;
import de.metafinanz.mam.backend.repository.User;

public class UserControllerImpl implements UserController {

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
	public void resetPwd(Long id) {
		User user = User.findUser(id);
		if (user == null) {
			throw new IllegalArgumentException("User not found.");
		} else {
			resetPwd(user);
		}
	}

	/**
	 * Generate new Password and send Email.
	 * @param user
	 */
	private void resetPwd(User user) {
		// @TODO
	}

}