package restAssuredReference.copy888;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class postReference {

    public static void main(String[] args) {

        // Step 1: Declare Base URL
        RestAssured.baseURI = "https://reqres.in/";

        // Step 2: Configure Request Body
        /*   String requestBody1 = "{\r\n"
                + "    \"name\": \"morpheus\",\r\n"
                + "    \"job\": \"leader\"\r\n"
                + "}";   */
        
        // Step 3: Configure Response Body
        int statusCode=given().header("Content-Type","application/json").body("{\r\n"
    			+ "    \"name\": \"morpheus\",\r\n"
    			+ "    \"job\": \"leader\"\r\n"
    			+ "}").log().all().when().post("/api/users").then().log().all().extract().statusCode();

        String responseBody1 = given().header("Content-Type","application/json").body("{\r\n"
    			+ "    \"name\": \"morpheus\",\r\n"
    			+ "    \"job\": \"leader\"\r\n"
    			+ "}").log().all().when().post("/api/users").then().log().all().extract().response().asString();

        // Step 4: Parse the response body
        JsonPath jsp = new JsonPath(responseBody1);
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_id = jsp.getString("id");
        String res_createdAt = jsp.getString("createdAt");

        // Step 5: Validate the response body parameters
        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(res_name, "morpheus");
        Assert.assertEquals(res_job, "leader");

        // Validate "id" and "createdAt" using Assert
        Assert.assertNotNull(res_id);
        Assert.assertNotNull(res_createdAt);

        // Validate "createdAt" using slice method for date
        String expectedDate = new java.util.Date().toInstant().toString().substring(0, 10);
        String actualDate = res_createdAt.substring(0, 10);
        Assert.assertEquals(actualDate, expectedDate);
        

    	System.out.println("Status code is: "+ statusCode + " Created");
    	// System.out.println(requestBody1);
    	System.out.println(responseBody1);
        
    }
}
