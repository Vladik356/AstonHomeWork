import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class PostRequestTest extends BaseTest {

    @Test
    public void testPostMethod() {
        given()
                .contentType("application/json")
                .body("{\"foo\":\"bar\"}")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.foo", equalTo("bar"));
    }
}
