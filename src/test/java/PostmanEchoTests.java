import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanEchoTests {

    private final String BASE_URL = "https://postman-echo.com";

    @Test
    public void testGetMethod() {
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("args", new JSONObject());
        expectedResponse.put("headers", new JSONObject()); // Заполните ожидаемые заголовки
        expectedResponse.put("url", BASE_URL + "/get");

        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(expectedResponse.toString(), jsonResponse.toString());
    }

    @Test
    public void testPostMethod() {
        RestAssured.baseURI = BASE_URL;

        JSONObject requestBody = new JSONObject();
        requestBody.put("foo", "bar");

        Response response = given()
                .body(requestBody.toString())
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("data", requestBody.toString());

        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(expectedResponse.getString("data"), jsonResponse.getString("data"));
    }

    @Test
    public void testPutMethod() {
        RestAssured.baseURI = BASE_URL;

        JSONObject requestBody = new JSONObject();
        requestBody.put("foo", "bar");

        Response response = given()
                .body(requestBody.toString())
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("data", requestBody.toString());

        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(expectedResponse.getString("data"), jsonResponse.getString("data"));
    }

    @Test
    public void testDeleteMethod() {
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("args", new JSONObject());
        expectedResponse.put("headers", new JSONObject()); // Заполните ожидаемые заголовки
        expectedResponse.put("url", BASE_URL + "/delete");

        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(expectedResponse.toString(), jsonResponse.toString());
    }

    @Test
    public void testPatchMethod() {
        RestAssured.baseURI = BASE_URL;

        JSONObject requestBody = new JSONObject();
        requestBody.put("foo", "bar");

        Response response = given()
                .body(requestBody.toString())
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("data", requestBody.toString());

        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(expectedResponse.getString("data"), jsonResponse.getString("data"));
    }
}
