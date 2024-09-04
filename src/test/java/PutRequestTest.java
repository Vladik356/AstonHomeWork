import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class PutRequestTest extends BaseTest {

    @Test
    public void testPutMethod() {
        given()
                .contentType("application/json")
                .body("{\"foo\":\"bar\"}")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.foo", equalTo("bar"));
    }
}
