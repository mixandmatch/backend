package client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TestAppointments {

//	private static String URL = "http://ec2-54-246-100-223.eu-west-1.compute.amazonaws.com:8080/MixMatchRooRestTestService";
	private static String URL = "http://localhost:8080/mam-backend-0.0.1.BUILD-SNAPSHOT-dev/rest";
	private static String RESOURCE = "/appointments";
	
	private WebResource webResource;

	@Before
	public void before(){
		Client client = Client.create();
		webResource = client.resource(URL + RESOURCE);
	}
	
	@Test
	public void test(){
		System.out.println("======== " + TestAppointments.class.getSimpleName() + " ========");
		
		get();
	
		String json = "{\"appointmentDate\":1389771678807,\"appointmentLocation\":1,\"ownerID\":1}";
		post(json);
		
		json = get();
		
		String id = getResourceID(json, "appointmentID");
		
		json = "{\"username\":\"Marion\"}";
		postParticipant(id, json);
		
		get();
		
		json = "{\"appointmentID\":" + id + ",\"appointmentDate\":1389771678807,\"appointmentLocation\":{\"locationID\":2,\"locationName\":\"location 2\",\"version\":0},\"ownerID\":{\"id\":1,\"username\":\"user 1\",\"version\":0},\"participants\":[{\"id\":13,\"username\":\"Marion\",\"version\":0}],\"version\":1}";
		put(id, json);
		
		get();
		
		getByAppointmentLocation("2");
		
		delete(id);
		
		get();
		
	}

	private String postParticipant(String id, String json) {
		System.out.println();
		System.out.println("======== POST Participant ========");
		System.out.println(json);
		ClientResponse response = webResource.path("/" + id + "/addParticipant").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,
						json);
		return response.toString();
		
	}

	private String getResourceID(String json, String key){
		JSONTokener tokener = new JSONTokener(json);
		JSONArray array = new JSONArray(tokener);
		JSONObject obj = (JSONObject) array.get(array.length()-1);
		Integer id = (Integer) obj.get(key);
		return id.toString();
	}
	
	private String get() {
		System.out.println();
		System.out.println("======== GET ========");
		String json = webResource.accept(MediaType.APPLICATION_JSON).get(
				String.class);
		System.out.println(json);
		return json;
	}
	
	private String post(String json){
		System.out.println();
		System.out.println("======== POST ========");
		System.out.println(json);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,
						json);
		return response.toString();
	}
	
	private String put(String id, String json){
		System.out.println();
		System.out.println("======== PUT ========");
		System.out.println(json);
		ClientResponse response = webResource.path("/" + id)
				.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,
						json);
		return response.toString();
	}
	
	private String getByAppointmentLocation(String search) {
		System.out.println();
		System.out.println("======== GET BY AppointmentLocation ========");
		System.out.println("search: " + search );
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("find", "ByAppointmentLocation");
		queryParams.add("appointmentLocation", search);
		String json = webResource.queryParams(queryParams).accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(json);
		return json;
	}
	
	private String delete(String id) {
		System.out.println();
		System.out.println("======== DELETE ========");
		System.out.println("resourceID: " + id);
		ClientResponse response = webResource.path("/" + id).delete(ClientResponse.class);
		return response.toString();
	}
}
