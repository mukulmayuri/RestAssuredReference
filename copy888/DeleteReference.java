package restAssuredReference.copy888;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class DeleteReference {

	public static void main(String[] args) {
		 // Step 1:Declare BaseURL
        RestAssured.baseURI = "https://reqres.in/";
        
        int statusCode3 = given().header("Content-Type","application/json").log().all().when().delete("/api/users/2").then().log().all().extract().statusCode();
        String responseBody3 = given().header("Content-Type","application/json").log().all().when().delete("/api/users/2").then().log().all().extract().response().asString();
        System.out.println(responseBody3);
        System.out.println("Status code is: "+ statusCode3 + " No Content");
        
	}
	
};
