import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class PatchRequestTest extends BaseTest {

    @Test
    public void testPatchMethod() {
        given()
                .contentType("application/json")
                .body("{\"foo\":\"bar\"}")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.foo", equalTo("bar"));
    }
}
