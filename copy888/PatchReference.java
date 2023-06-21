package restAssuredReference.copy888;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PatchReference {

	public static void main(String[] args) {
		
		//Step 1 : Declare Base URL
		RestAssured.baseURI= "https://reqres.in";
		
		//Step 2: Configure Request Body
		
		int statusCode=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().patch("/api/users/2").then().extract().statusCode();
		
		//Without Log all
		String responseBody = given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().patch("/api/users/2").then().extract().response().asString();
		
		System.out.println(statusCode);
		System.out.println(responseBody);
		
		//Step :3 Parse the response body
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		//String res_id = jsp.getString("id");
		String res_updatedAt=jsp.getString("updatedAt");
		
		//Step 4: Validate the response body parameters
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		//Assert.assertNotNull(res_id);
		
		String trimming = res_updatedAt.substring(0,10);
		System.out.println("Trimming " +trimming);
		
		String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE).substring(0, 10);
	    Assert.assertEquals(trimming,date);
	    
	  }
			
	}
