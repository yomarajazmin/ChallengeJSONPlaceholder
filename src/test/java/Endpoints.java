import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.PostPayload;

public class Endpoints {

    public static Response getPosts() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(Paths.GET_POSTS);
    }

    public static Response addPost(String userId, String title, String body) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(PostPayload.getPostPayload(userId, title, body, null))
                .when()
                .post(Paths.CREATE_POSTS);
    }

    public static Response updatePost(String userId, String title, String body, String id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .body(PostPayload.putPostPayload(userId, title, body, id))
                .when()
                .post(Paths.UPDATE_POST_BY_ID);
    }

    public static Response deletePost(String id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .post(Paths.DELETE_POST_BY_ID);
    }

    public static Response getPost(String id) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .post(Paths.GET_POST_BY_ID);
    }
}
