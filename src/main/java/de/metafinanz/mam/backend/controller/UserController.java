package de.metafinanz.mam.backend.controller;

import java.io.IOException;
import java.util.List;

import de.metafinanz.mam.backend.repository.User;
 
public interface UserController{
 
	User getOrCreateUser(User aUser);
	List<User> getUsers();
//	String deleteUser();
	User findUserByID(Long userID);
	List<User> searchUser(String username);
	User createUser(User aUser);
	void resetPwd(String username) throws IOException;
	User uploadPicture(byte[] picture, User aUser);
	void setNewPassword(String token, String newPassword);
 
}