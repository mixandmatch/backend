package de.metafinanz.mam.backend.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metafinanz.mam.backend.controller.UserController;

@Component
@Path("/users")
public class UserService {

	@Autowired
	UserController userController;

//	@GET
//	public Response get() {
//		String result = userController.;
//		return Response.status(200).entity(result).build();
//	}

	@GET
	@Path("/add")
	public Response addUser() {
		String result = userController.addUser();
		return Response.status(200).entity(result).build();
	}

}