package de.metafinanz.mixnmatch.backend.rest;

public class EventNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -967861219655447159L;
	private String url;

	public EventNotFoundException(String url) {
		super("Event URL " + url + " not found");
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
