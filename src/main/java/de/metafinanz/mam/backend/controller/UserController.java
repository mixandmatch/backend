package de.metafinanz.mam.backend.controller;

import de.metafinanz.mam.backend.repository.User;
 
public interface UserController{
 
	User getOrCreateUser(User aUsername);
//	String deleteUser();
	User findUserByID(Long userID);
 
}