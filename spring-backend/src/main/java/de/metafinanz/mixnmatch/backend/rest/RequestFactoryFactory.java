package de.metafinanz.mixnmatch.backend.rest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.http.client.CommonsClientHttpRequestFactory;

public class RequestFactoryFactory {

	private String host;

	private int port = 80;

	private String user;

	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public CommonsClientHttpRequestFactory createClient() {
		HttpClient client = new HttpClient();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
				getUser(), getPassword());
		client.getState().setCredentials(
				new AuthScope(host, port), credentials);
		return new CommonsClientHttpRequestFactory(client);
	}
}
