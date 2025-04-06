package restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostApiTest {

    @Test
    public void createUserTest() {
        RestAssured.baseURI = "https://reqres.in";

        // Create JSON body
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Automation Account");
        requestBody.put("job", "Tester");

        // Send POST request
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post("/api/users");

        // Print response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().prettyPrint());

        // Assertions (optional)
        assertEquals(201, response.getStatusCode());
        assertEquals("Automation Account", response.jsonPath().getString("name"));
    }
}
