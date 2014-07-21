package client;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TestCanteen {
	static Logger logger = LoggerFactory.getLogger(TestCanteen.class);

	// private static String URL =
	// "http://ec2-54-246-100-223.eu-west-1.compute.amazonaws.com:8080/MixMatchRooRestTestService";
	private static String URL = "http://localhost:8080/mam-backend-0.0.1.BUILD-SNAPSHOT-dev/rest";
	private static String RESOURCE = "/canteen";

	private WebResource webResource;

	@Before
	public void before() {
		Client client = Client.create();
		webResource = client.resource(URL + RESOURCE);
	}

	@Test
	public void test() {
		logger.info("======== " + TestCanteen.class.getSimpleName() + " ========");

		get();

		String json = "{\"name\":\"Berlin\"}";
		logger.info(post(json));

		json = get();

		String id = getResourceID(json, "id");

		json = "{\"id\":" + id + ",\"name\":\"Dresden\",\"version\":0}";
		put(id, json);

		get();

		getByLocationName("dre");

		getById(id);

		delete(id);

		get();

	}

	private String getResourceID(String json, String key) {
		JSONTokener tokener = new JSONTokener(json);
		JSONArray array = new JSONArray(tokener);
		JSONObject obj = (JSONObject) array.get(array.length() - 1);
		Integer id = (Integer) obj.get(key);
		return id.toString();
	}

	private String get() {
		logger.info("======== GET ========");
		String json = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
		logger.info(json);
		return json;
	}

	private String getById(String id) {
		logger.info("======== GET BY ID ========");
		logger.info("id: " + id);
		String json = webResource.path("/" + id).accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		logger.info(json);
		return json;
	}

	private String post(String json) {
		logger.info("======== POST ========");
		logger.info(json);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		return response.toString();
	}

	private String put(String id, String json) {
		logger.info("======== PUT ========");
		logger.info(json);
		ClientResponse response = webResource.path("/" + id).accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, json);
		return response.toString();
	}

	private String getByLocationName(String locationName) {
		logger.info("======== GET BY LOCATIONNAME ========");
		logger.info("search: " + locationName);
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("find", "ByLocationNameLike");
		queryParams.add("name", locationName);
		String json = webResource.queryParams(queryParams).accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		logger.info(json);
		return json;
	}

	private String delete(String id) {
		logger.info("======== DELETE ========");
		logger.info("resourceID: " + id);
		ClientResponse response = webResource.path("/" + id).delete(ClientResponse.class);
		return response.toString();
	}
	
	
}
