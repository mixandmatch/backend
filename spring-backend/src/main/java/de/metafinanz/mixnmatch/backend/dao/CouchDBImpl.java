package de.metafinanz.mixnmatch.backend.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import de.metafinanz.mixnmatch.backend.dao.couchDB.CouchEventRequest;
import de.metafinanz.mixnmatch.backend.dao.couchDB.KeyEventPair;
import de.metafinanz.mixnmatch.backend.dao.couchDB.RequestQueryResult;
import de.metafinanz.mixnmatch.backend.model.EventRequest;
import de.metafinanz.mixnmatch.backend.model.Location;

public class CouchDBImpl implements MixandmatchDao {

	private RestTemplate restTemplate;
	private String baseUri;
	private String database;

	public void saveRequest(EventRequest pEventRequest) {
		UUID uuid = java.util.UUID.randomUUID();
		String url = baseUri + "/" + database + "/" + uuid;
		restTemplate.put(url, pEventRequest);
	}

	public List<EventRequest> getAllRequests() {
		RequestQueryResult requestQueryResult = restTemplate.getForObject(baseUri+"/"+database+"/_design/request_views/_view/all", RequestQueryResult.class);
		LinkedList<EventRequest> result = new LinkedList<EventRequest>();
		for (KeyEventPair keyValuePair : requestQueryResult.getRows()) {
			CouchEventRequest value = keyValuePair.getValue();
			result.add(value);
		}
		return result;
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

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDatabase() {
		return database;
	}

}
