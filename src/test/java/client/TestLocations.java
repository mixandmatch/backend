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

public class TestLocations {

//	private static String URL = "http://ec2-54-246-100-223.eu-west-1.compute.amazonaws.com:8080/MixMatchRooRestTestService";
	private static String URL = "http://localhost:8080/mam-backend-0.0.1.BUILD-SNAPSHOT-dev/rest";
	private static String RESOURCE = "/locations";
	
	private WebResource webResource;

	@Before
	public void before(){
		Client client = Client.create();
		webResource = client.resource(URL + RESOURCE);
	}
	
	@Test
	public void test(){
		System.out.println("======== " + TestLocations.class.getSimpleName() + " ========");
		
		get();
		
		String json = "{\"locationName\":\"Berlin\"}";
		post(json);
		
		json = get();
		
		String id = getResourceID(json, "locationID");
		
		json = "{\"locationID\":" + id + ",\"locationName\":\"Dresden\",\"version\":0}";
		put(id, json);
		
		get();
		
		getByLocationName("dre");
		
		getById(id);
		
		delete(id);
		
		get();
		
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
	
	private String getById(String id) {
		System.out.println();
		System.out.println("======== GET BY ID ========");
		System.out.println("id: " + id );
		String json = webResource.path("/" + id).accept(MediaType.APPLICATION_JSON).get(
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
	
	private String getByLocationName(String locationName) {
		System.out.println();
		System.out.println("======== GET BY LOCATIONNAME ========");
		System.out.println("search: " + locationName );
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("find", "ByLocationNameLike");
		queryParams.add("locationName", locationName);
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
