import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class PostsTests {

    @Test
    @Description("Test Description : Retrieve list of publications.")
    public void getAListOfPosts() {
        Response response = Endpoints.getPosts();
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        JSONArray responseArray = new JSONArray(response.getBody().asString());
        Assertions.assertTrue(responseArray.length() > 0);

        JSONObject firstElement = responseArray.getJSONObject(0);
        Assertions.assertTrue(firstElement.has("userId"));
        Assertions.assertTrue(firstElement.has("id"));
        Assertions.assertTrue(firstElement.has("title"));
        Assertions.assertTrue(firstElement.has("body"));
    }

    @Test
    @Description("Test Description : Create a post.")
    public void createAPost() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String testTitle = "Test Title " + timestamp;
        String testBody = "Test Body " + timestamp;
        Response response = Endpoints.addPost(timestamp, testTitle, testBody);
        response.then().log().all();
        Assertions.assertEquals(201, response.getStatusCode());

        JSONObject result = new JSONObject(response.getBody().asString());
        Assertions.assertTrue(result.has("id"));
        Assertions.assertEquals(testTitle, result.getString("title"), "Response title is not the expected.");
        Assertions.assertEquals(testBody, result.getString("body"), "Response body is not the expected.");
        Assertions.assertEquals(timestamp, result.get("userId").toString(), "Response userId is not the expected.");
    }

    @Test
    @Description("Test Description : Update a post.")
    public void updateAPost() {
        // CREATE POST
        String timestamp = String.valueOf(System.currentTimeMillis());
        String testTitle = "Test Title " + timestamp;
        String testBody = "Test Body " + timestamp;
        Response response = Endpoints.addPost(timestamp, testTitle, testBody);
        response.then().log().all();
        Assertions.assertEquals(201, response.getStatusCode());

        JSONObject result = new JSONObject(response.getBody().asString());
        Assertions.assertTrue(result.has("id"));
        String testId = result.get("id").toString();

        //UPDATE POST
        testTitle = "Update Title " + timestamp;
        testBody = "Update Body " + timestamp;
        response = Endpoints.updatePost(timestamp, testTitle, testBody, testId);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        result = new JSONObject(response.getBody().asString());
        Assertions.assertTrue(result.has("id"));
        Assertions.assertEquals(testTitle, result.getString("title"), "Response title is not the expected.");
        Assertions.assertEquals(testBody, result.getString("body"), "Response body is not the expected.");
        Assertions.assertEquals(timestamp, result.get("userId").toString(), "Response userId is not the expected.");
    }

    @Test
    @Description("Test Description : Delete a post.")
    public void deleteAPost() {
        // CREATE POST
        String timestamp = String.valueOf(System.currentTimeMillis());
        String testTitle = "Test Title " + timestamp;
        String testBody = "Test Body " + timestamp;
        Response response = Endpoints.addPost(timestamp, testTitle, testBody);
        response.then().log().all();
        Assertions.assertEquals(201, response.getStatusCode());

        JSONObject result = new JSONObject(response.getBody().asString());
        Assertions.assertTrue(result.has("id"));
        String testId = result.get("id").toString();

        //DELETE POST
        response = Endpoints.deletePost(testId);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        //GET POST
        response = Endpoints.getPost(testId);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        result = new JSONObject(response.getBody().asString());
        Assertions.assertFalse(result.has("id"), "Post was not deleted.");
        Assertions.assertFalse(result.has("title"), "Post was not deleted.");
        Assertions.assertFalse(result.has("body"), "Post was not deleted.");
        Assertions.assertFalse(result.has("userId"), "Post was not deleted.");
    }
}
