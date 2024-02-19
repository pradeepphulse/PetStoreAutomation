package api.endpoints;

/*
Swagger URI: https://petstore.swagger.io

Create User: https://petstore.swagger.io/v2/user
Get User:    https://petstore.swagger.io/v2/user/{username}
Update User: https://petstore.swagger.io/v2/user/{username}
Delete User: https://petstore.swagger.io/v2/user/{username} 

 */

//maintain all the urls and endpoints in this class.

public class Routes {
	
	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	//User module
	
	public static String postUrl = baseUrl+"/user";
	public static String getUrl = baseUrl+"/user/{username}";
	public static String putUrl = baseUrl+"/user/{username}";
	public static String deleteUrl = baseUrl+"/user/{username}";

}
