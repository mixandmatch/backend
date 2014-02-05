package de.metafinanz.mam.backend.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.UserController;
import de.metafinanz.mam.backend.repository.User;

@Component
@Path("/users")
public class UserService {

	@Autowired
	UserController userController;

	// @GET
	// public Response get() {
	// String result = userController.;
	// return Response.status(200).entity(result).build();
	// }

	@POST
	public Response CreateOrGetUser(User aUser) {
		User result = userController.CreateOrGetUser(aUser.getUsername());
		return Response.status(200).entity(result).build();
	}

}