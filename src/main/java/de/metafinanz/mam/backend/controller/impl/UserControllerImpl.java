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
	public User getOrCreateUser(User aUser) {
		return User.getOrCreateUser(aUser);
	}

	@Override
	public List<User> getUsers() {
		return User.findAllUsers();
	}

}