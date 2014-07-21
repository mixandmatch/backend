package client;

import java.util.Collection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import de.metafinanz.mam.backend.controller.UserController;
import de.metafinanz.mam.backend.controller.impl.UserControllerImpl;
import de.metafinanz.mam.backend.repository.Token;
import de.metafinanz.mam.backend.repository.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
public class TestUsers {

	// private static String URL =
	// "http://ec2-54-246-100-223.eu-west-1.compute.amazonaws.com:8080/MixMatchRooRestTestService";
	private static String URL = "http://localhost:8080/mam-backend-0.0.1.BUILD-SNAPSHOT-dev/rest";
	private static String RESOURCE = "/users";

	private WebResource webResource;
	private Token token;
	private User user;

	@Before
	public void before() {
		Client client = Client.create();
		webResource = client.resource(URL + RESOURCE);
		
		user = new User();
		user.setEMail("bla@Blub.de");
		user.setEnabled(true);
		user.setUsername("tester");
		user.setPassword("123");
		user.persist();
		
		token = new Token(user);
		token.persist();
	}

	@Test
	public void test() {
		System.out.println("======== " + TestUsers.class.getSimpleName() + " ========");

		get();

		String json = "{\"username\":\"Max\"}";
		post(json);

		json = get();

		String id = getResourceID(json, "id");

		json = "{\"id\":" + id + ",\"username\":\"Marion\",\"version\":0}";
		put(id, json);

		get();

		getByUserName("ion");

		// Not available at the moment:
		// getById(id);

		// delete(id);

		get();

	}

	@Test
	public void testGet() {
		String result = get();
		Collection<User> users = User.fromJsonArrayToUsers(result);

		// TODO: Currently this test depends on the generated testdata generated
		// by the SampleDataGenerator.
		Assert.assertEquals(users.size(), 11);

		// for (Iterator<User> iterator = users.iterator(); iterator.hasNext();)
		// {
		// User user = (User) iterator.next();
		// System.out.println(user.toString());
		// }

	}

	@Test
	public void testCreateOrGetUser() {
		// Create a new user:
		String user = "{\"username\":\"Max\"}";
		ClientResponse result = post(user);
		Assert.assertEquals(result.getStatus(), 201);
		System.out.println(result.toString());
		
		String returnedUser = result.getEntity(String.class);
		System.out.println(returnedUser.toString());

		// Existing user with ID:
		user = "{\"id\":1}";
		result = post(user);
		Assert.assertEquals(result.getStatus(), 304);
		System.out.println(result.toString());
		
//		Existing User with username:
		user = "{\"username\":\"user 1\"}";
		result = post(user);
		Assert.assertEquals(result.getStatus(), 304);
		System.out.println(result.toString());
	}

	private String getResourceID(String json, String key) {
		JSONTokener tokener = new JSONTokener(json);
		JSONArray array = new JSONArray(tokener);
		JSONObject obj = (JSONObject) array.get(array.length() - 1);
		Integer id = (Integer) obj.get(key);
		return id.toString();
	}

	private String get() {
		System.out.println();
		System.out.println("======== GET ========");
		String json = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(json);
		return json;
	}

	private String getById(String id) {
		System.out.println();
		System.out.println("======== GET BY ID ========");
		System.out.println("id: " + id);
		String json = webResource.path("/" + id).accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(json);
		return json;
	}

	private ClientResponse post(String json) {
		System.out.println();
		System.out.println("======== POST ========");
		System.out.println(json);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
		return response;
	}

	private String put(String id, String json) {
		System.out.println();
		System.out.println("======== PUT ========");
		System.out.println(json);
		ClientResponse response = webResource.path("/" + id).accept(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, json);
		return response.toString();
	}

	private String getByUserName(String search) {
		System.out.println();
		System.out.println("======== GET BY LOCATIONNAME ========");
		System.out.println("search: " + search);
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("find", "ByUsernameLike");
		queryParams.add("username", search);
		String json = webResource.queryParams(queryParams).accept(MediaType.APPLICATION_JSON)
				.get(String.class);
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
	
	@Test
	public void testSetNewPassword() {
		User user = User.findUsersByUsernameEquals("tester").getSingleResult();
		if (user != null) {
			String oldEncryptedPassword = user.getPassword();
			UserController ctrl = new UserControllerImpl();
			ctrl.setNewPassword(token.getTokenString(), "111");

			user = User.findUsersByUsernameEquals("tester").getSingleResult();
			Assert.assertNotEquals(oldEncryptedPassword, user.getPassword());
		}
	}
}
