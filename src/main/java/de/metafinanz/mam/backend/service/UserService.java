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
		logger.trace("entering UserService.users()");
		return userController.getUsers();
	}

	/**
	 * Creates or returns a user. If the user does not exist it is created otherwise it is returned.
	 * @param aUser User JSON with either username or ID. If the user should be created a username has to be supplied.
	 * @return HTTP Status Created if the user did not exist, NOT_MODIFIED if it did.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrCreateUser(User aUser) {
//		TODO: Return a redirect to the specific user URL
		logger.trace("entering UserService.getOrCreateUser()");
		User result = null;

		try {
			result = userController.getOrCreateUser(aUser);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML)
					.entity(e.getMessage()).build();
		}

		if (result == null) {
			// Should never happen
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		if (result.isGetOrCreateUserCreated()) {
			// User was created:
			return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON).entity(result)
					.build();
		} else {
			// User existed already:
			return Response.status(Status.NOT_MODIFIED).entity(result).build();
		}

	}

}