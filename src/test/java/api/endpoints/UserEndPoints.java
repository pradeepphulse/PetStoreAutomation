package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import api.payload.User;


//This class is to perform crud oprations to thr user API.
public class UserEndPoints {
	

  // methos to return the properties file data.	
  public static ResourceBundle getUrl(){
	  ResourceBundle routes = ResourceBundle.getBundle("routes"); //loads the propertoes file
	  return routes;
  }

	public static Response createUser(User payload){
		
		String post_Url = getUrl().getString("postUrl");
		
		Response response = given()
		                    .contentType(ContentType.JSON)
		                    .accept(ContentType.JSON)
		                   
		                    .body(payload)
		          .when()
		           .post(post_Url);

		
		return response;
	}
	
	public static Response getUser(String username){
		
		String get_Url = getUrl().getString("getUrl");
		
		Response response = given()
		                   .pathParam("username", username)
		         .when()
		         .get(get_Url);
		
		return response;
	
}
	
public static Response updateUser(User payload,String username){
	
	String put_Url = getUrl().getString("putUrl");
		
		Response response = given()
				           .contentType(ContentType.JSON)
                           .accept(ContentType.JSON)
		                   .pathParam("username",username)
		                   .body(payload)
		         .when()
		         .put(put_Url);
		System.out.println("name is - "+username);
		
		
		return response;
	
}

public static Response deleteUser(String username){
	
	String delete_Url = getUrl().getString("deleteUrl");
	
	Response response = given()
	                   .pathParam("username",username)
	         .when()
	         .delete(delete_Url);
	
	
	return response;

}
}
