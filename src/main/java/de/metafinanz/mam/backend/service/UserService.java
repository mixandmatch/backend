package de.metafinanz.mam.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.UserController;
import de.metafinanz.mam.backend.repository.User;

@Component
@Path("users")
public class UserService {

	@Autowired
	UserController userController;

	static Logger logger = LoggerFactory.getLogger(UserService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> users() {
		logger.trace("entering users()");
		return userController.getUsers();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrCreateUser(User aUser) {

		User result = null;

		try {
			result = userController.getOrCreateUser(aUser);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (result == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(result, MediaType.APPLICATION_JSON).build();

		// TODO Modify getOrCreateUser so that the corresponding status can be
		// used:
		// Return if user was created:
		// Response.created(location)
		// return Response.status(Status.CREATED).entity(result).build();

		// Return if user already exists in DB:
		// Response.notModified()
		// return Response.status(Status.NOT_MODIFIED).entity(result).build();
	}

}