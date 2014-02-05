package de.metafinanz.mam.backend.controller;

import de.metafinanz.mam.backend.repository.User;
 
public interface UserController{
 
	String addUser();
	String deleteUser();
	User findUserByName(String aUsername);
	User findUserByID(Long userID);
 
}