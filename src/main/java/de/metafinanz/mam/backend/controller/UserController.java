package de.metafinanz.mam.backend.controller;

import java.util.List;

import de.metafinanz.mam.backend.repository.User;
 
public interface UserController{
 
	User getOrCreateUser(User aUser);
	List<User> getUsers();
//	String deleteUser();
	User findUserByID(Long userID);
 
}