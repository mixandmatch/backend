package de.metafinanz.mam.backend.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	public Response getOrCreateUser(User aUser) {
		User result = userController.getOrCreateUser(aUser);
		// TODO Catch possible IllegalArgumentException from
		// de.metafinanz.mam.backend.repository.User.getOrCreateUser(User)
		// TODO Return status of getOrCreateUser so that the corresponding
		// status can be used:
		// Return if user was created:
		return Response.status(Status.CREATED).entity(result).build();

		// Return if user already exists in DB:
		// return Response.status(Status.NOT_MODIFIED).entity(result).build();
	}

}