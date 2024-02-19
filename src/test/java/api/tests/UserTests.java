package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	
	public Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("*****************Creating the user*********************");
		Response response = UserEndPoints.createUser(userPayload);

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****************User is created*********************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("*****************Getting the user*********************");

		Response response = UserEndPoints.getUser(this.userPayload.getUsername());

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*****************Got the user *********************");

	}
	
	@Test(priority = 3)
	public void testPutUser() {
		
		logger.info("*****************updating the user*********************");
		
		//updating the payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//getting the user after updating
		Response responseAfterUpdate = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("*****************Updated the user*********************");
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("*****************Deleting the user*********************");

		Response response = UserEndPoints.deleteUser(userPayload.getUsername());

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*****************Deleted the user*********************");

	}
}
