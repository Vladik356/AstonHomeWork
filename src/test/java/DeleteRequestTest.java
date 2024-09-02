import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteRequestTest extends BaseTest {

    @Test
    public void testDeleteMethod() {
        when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json", nullValue());
    }
}
