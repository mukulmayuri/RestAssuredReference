package restAssuredReference.copy888;

import org.testng.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;


public class PutReference {

	public static void main(String[] args) {
        
        // Step 1:Declare BaseURL
        RestAssured.baseURI = "https://reqres.in/";
        
        // Step 2:Configure Request Body
        String requestBody = "{\r\n"
        		+ "    \"name\": \"morpheus\",\r\n"
        		+ "    \"job\": \"zion resident\"\r\n"
        		+ "}";
        System.out.println(requestBody);
        int statusCode1 = given().header("Content-Type","application/json").body("{\r\n"
        		+ "    \"name\": \"morpheus\",\r\n"
        		+ "    \"job\": \"zion resident\",\r\n"
        		+ "    \"updatedAt\": \"2023-05-05T08:44:33.246Z\"\r\n"
        		+ "}").log().all().when().put("/api/users/2").then().log().all().extract().statusCode();
             
        String responseBody = given().header("Content-Type","application/json").body("{\r\n"
        		+ "    \"name\": \"morpheus\",\r\n"
        		+ "    \"job\": \"zion resident\",\r\n"
        		+ "    \"updatedAt\": \"2023-05-05T08:44:33.246Z\"\r\n"
        		+ "}").log().all().when().put("/api/users/2").then().log().all().extract().response().asString();
        	System.out.println(responseBody);
        JsonPath jsp = new JsonPath(responseBody);
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_updatedAt = jsp.getString("updatedAt");
        System.out.println("Status code is: "+ statusCode1 + " OK");  
        System.out.println("name : "+res_name);
        System.out.println("job : "+res_job);
        System.out.println("updatedAt : "+res_updatedAt);
        
        //Validate response body
        Assert.assertEquals(res_name,"morpheus");
        Assert.assertEquals(res_job,"zion resident");
        
    }
};