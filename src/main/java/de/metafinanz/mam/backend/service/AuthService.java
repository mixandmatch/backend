package de.metafinanz.mam.backend.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("restAuthenticationEntryPoint")
@Path("login")
public class AuthService implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
