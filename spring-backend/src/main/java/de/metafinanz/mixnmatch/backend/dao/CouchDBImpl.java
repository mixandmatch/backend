package de.metafinanz.mixnmatch.backend.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import de.metafinanz.mixnmatch.backend.model.EventRequest;
import de.metafinanz.mixnmatch.backend.model.Location;

public class CouchDBImpl implements MixandmatchDao {

	private RestTemplate restTemplate;
	private String baseUri;

	public void saveRequest(EventRequest pEventRequest) {
		UUID uuid = java.util.UUID.randomUUID();
		String url = baseUri+"/requests/" + uuid;
		restTemplate.put(url, pEventRequest);
	}

	public List<EventRequest> getAllRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Location> getAllLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addLocation(Location pLocation) {
		// TODO Auto-generated method stub

	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public String getBaseUri() {
		return baseUri;
	}

}
