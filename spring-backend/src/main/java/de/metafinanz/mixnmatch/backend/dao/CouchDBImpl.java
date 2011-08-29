package de.metafinanz.mixnmatch.backend.dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import de.metafinanz.mixnmatch.backend.dao.couchDB.CouchEventRequest;
import de.metafinanz.mixnmatch.backend.dao.couchDB.KeyEventPair;
import de.metafinanz.mixnmatch.backend.dao.couchDB.RequestQueryResult;
import de.metafinanz.mixnmatch.backend.model.EventRequest;
import de.metafinanz.mixnmatch.backend.model.Location;
import de.metafinanz.mixnmatch.backend.model.Match;

public class CouchDBImpl implements MixandmatchDao {

	private static final String VIEW_ALL = "/_design/request_views/_view/all";
	private static final String SEPARATOR = "/";
	private static final String VIEW_BY_USER = "/_design/request_views/_view/by_user";
	private static final String VIEW_BY_LOCATION_DATE = "/_design/request_views/_view/by_location_date";
	private static final String VIEW_BY_LOCATION = "/_design/request_views/_view/by_location";
	private static final String VIEW_ALL_MATCHES = "/_design/match_views/_view/all_matches?group=true";
	private RestTemplate restTemplate;
	private String baseUri;
	private String database;

	private String getUrl() {
		return baseUri + SEPARATOR + database;
	}

	private List<CouchEventRequest> queryRequestView(String viewDocument,
			String... keys) {
		String keyJsonArray = null;
		if (keys.length > 0) {
			StringBuilder keyBuilder = new StringBuilder("[");
			for (String key : keys) {
				keyBuilder.append("\"");
				keyBuilder.append(key);
				keyBuilder.append("\",");
			}
			keyBuilder.replace(keyBuilder.length() - 1, keyBuilder.length(),
					"]");
			keyJsonArray = keyBuilder.toString();
		}
		RequestQueryResult requestQueryResult = null;
		if (keyJsonArray != null) {
			requestQueryResult = restTemplate.getForObject(getUrl()
					+ viewDocument + "?key={key}", RequestQueryResult.class,
					keyJsonArray);
		} else {
			requestQueryResult = restTemplate.getForObject(getUrl()
					+ viewDocument, RequestQueryResult.class);
		}
		LinkedList<CouchEventRequest> result = new LinkedList<CouchEventRequest>();
		for (KeyEventPair keyValuePair : requestQueryResult.getRows()) {
			CouchEventRequest value = keyValuePair.getValue();
			result.add(value);
		}
		return result;
	}

	public void saveRequest(EventRequest pEventRequest) {
		UUID uuid = java.util.UUID.randomUUID();
		String url = getUrl() + SEPARATOR + uuid;
		restTemplate.put(url, pEventRequest);
	}

	public List<? extends EventRequest> getAllRequests() {
		return queryRequestView(VIEW_ALL);
	}

	public EventRequest getRequest(String locationKey, String date, String userid) {
		String view = getUrl() + VIEW_ALL + "?key=[\"{locationKey}\",\"{date}\",\"{userid}\"]";
		RequestQueryResult requestQueryResult = restTemplate.getForObject(view,
				RequestQueryResult.class, locationKey, date, userid);
		return requestQueryResult.getRows().get(0).getValue();
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

	public Collection<? extends EventRequest> getRequestsByUser(String user) {
		return queryRequestView(VIEW_BY_USER, user);
	}

	public Collection<? extends EventRequest> getRequestsByLocationAndDate(
			String location, String date) {
		return queryRequestView(VIEW_BY_LOCATION_DATE, location.toLowerCase(), date);
	}

	public Collection<? extends EventRequest> getRequestsByLocation(String location) {
		return queryRequestView(VIEW_BY_LOCATION, location.toLowerCase());
	}

	public Collection<? extends EventRequest> listAllMatches() {
		Collection<CouchEventRequest> matches =  queryRequestView(VIEW_ALL_MATCHES);
		for (CouchEventRequest eventRequest : matches) {
			eventRequest.setType("match");
		}
		return matches;
	}

}
