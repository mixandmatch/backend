package de.metafinanz.mam.backend.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataParam;

import de.metafinanz.mam.backend.controller.UserController;
import de.metafinanz.mam.backend.repository.User;
import de.metafinanz.mam.backend.repository.json.JSONNewPassword;

@Component
@Path("user")
public class UserService extends BaseService{

	@Autowired
	UserController userController;

	static Logger logger = LoggerFactory.getLogger(UserService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> users() {
		logger.trace("entering UserService.users()");
		return userController.getUsers();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> search(@QueryParam("username") String username) {
		if (username != null) {
			return userController.searchUser(username);
		}

		return new ArrayList<User>();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User aUser) {

		try {
			User result = userController.createUser(aUser);
			if (result != null) {
				return Response.status(Status.CREATED)
						.type(MediaType.APPLICATION_JSON).entity(result)
						.build();
			} else {
				// Should never happen
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch (IllegalArgumentException e) {
			logger.info("User tried to register an already existing username.");
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			return Response.status(Status.CONFLICT)
					.type(MediaType.APPLICATION_JSON).entity(responseObj)
					.build();
		}
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/setNewPassword")
	public Response setNewPassword( @FormDataParam("token") String token,  @FormDataParam("newPassword") String newPassword) {
		try {
			userController.setNewPassword(token, newPassword);
			return Response.status(Status.OK).build();
		} catch (IllegalArgumentException e) {
			logger.error("Fehler beim setzen des neuen Passworts.", e);
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			return Response.status(Status.PRECONDITION_FAILED).build();
		} 
	}
	
	@GET
	@Path("/resetPwd")
	public Response resetPwd(@QueryParam("username") String username) {
		try {
			userController.resetPwd(username);
			return Response.status(Status.OK).build();
		} catch (IllegalArgumentException e) {
			logger.error("Fehler beim versenden des Token für Password Reset.", e);
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			return Response.status(Status.CONFLICT).entity(responseObj).build();
		} catch (IOException e) {
			logger.error("Fehler beim versenden des Token für Password Reset.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/picture")
	public Response uploadPicture(byte[] picture) {
		User user = getCurrentUser();

		User result = userController.uploadPicture(picture, user);
		if (result != null) {
			return Response.ok().entity(result).build();
		}

		return Response.status(Status.CONFLICT).build();
	}

	/**
	 * Creates or returns a user. If the user does not exist it is created
	 * otherwise it is returned.
	 * 
	 * EXAMPLE-JSON:<br/>
	 * <br/>
	 * 
	 * <code>{"username":"Random","password":"1234"}</code>
	 * 
	 * 
	 * @param aUser
	 *            User JSON with either username or ID. If the user should be
	 *            created a username has to be supplied.
	 * @return HTTP Status Created if the user did not exist, NOT_MODIFIED if it
	 *         did.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Deprecated
	public Response getOrCreateUser(User aUser) {
		// TODO: Return a redirect to the specific user URL
		logger.trace("entering UserService.getOrCreateUser()");
		User result = null;

		try {
			result = userController.getOrCreateUser(aUser);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.type(MediaType.TEXT_HTML).entity(e.getMessage()).build();
		}

		if (result == null) {
			// Should never happen
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		if (result.isGetOrCreateUserCreated()) {
			// User was created:
			return Response.status(Status.CREATED)
					.type(MediaType.APPLICATION_JSON).entity(result).build();
		} else {
			// User existed already:
			return Response.status(Status.NOT_MODIFIED).entity(result).build();
		}

	}

}