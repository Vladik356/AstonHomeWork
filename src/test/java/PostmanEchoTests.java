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

        // Создание ожидаемого JSON объекта
        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("args", new JSONObject());
        expectedResponse.put("headers", new JSONObject()); // Заполните ожидаемые заголовки
        expectedResponse.put("url", BASE_URL + "/get");

        // Преобразуем тело ответа в JSONObject
        JSONObject jsonResponse = new JSONObject(response.asString());

        // Проверяем, что JSON тело ответа совпадает с ожидаемым
        assertEquals(expectedResponse.toString(), jsonResponse.toString());
    }

    // Добавьте аналогичные тесты для POST, PUT, DELETE, PATCH методов
}
