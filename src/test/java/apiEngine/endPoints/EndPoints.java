package apiEngine.endPoints;

import org.apache.http.HttpStatus;

import apiEngine.model.request.AddListOfBooks;
import apiEngine.model.request.AuthorizationRequest;
import apiEngine.model.request.DeleteBook;
import apiEngine.model.response.Books;
import apiEngine.model.response.Token;
import apiEngine.model.response.UserAccount;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndPoints {

	private final RequestSpecification request;
	 
	 public EndPoints(String baseUrl) {
	        RestAssured.baseURI = baseUrl;
	        request = RestAssured.given();
	        request.header("Content-Type", "application/json");
	    }
	 	 
	 public void authenticateUser(AuthorizationRequest authRequest) {
	        Response response = request.body(authRequest).post(Route.generateToken());
			System.out.println("Env is :"+Route.VERSION);
	        if (response.statusCode() != HttpStatus.SC_OK)
	            throw new RuntimeException("Authentication Failed. Content of failed Response: " + response.toString() + " , Status Code : " + response.statusCode());
	 
	        Token tokenResponse = new RestResponse<Token>(Token.class, response).getBody();
//	        		response.body().jsonPath().getObject("$", Token.class);
	        request.header("Authorization", "Bearer " + tokenResponse.token);
	    }
	 
	 public IRestResponse<Books> getBooks() {
	        Response response = request.get(Route.books());
	        System.out.println(response.getBody().asString());
	        return new RestResponse<Books>(Books.class, response);
	    }
	 
	  public IRestResponse<UserAccount> addBook(AddListOfBooks addBooksRequest) {
	        Response response = request.body(addBooksRequest).post(Route.books());
	        return new RestResponse<UserAccount>(UserAccount.class, response);
	    }
	 
	  public Response removeBook(DeleteBook removeBookRequest) {
	        return request.body(removeBookRequest).delete(Route.book());
	   }
	 
	  public IRestResponse<UserAccount> getUserAccount(String userId) {
	        Response response = request.get(Route.userAccount(userId));
	        UserAccount ua = new RestResponse<UserAccount>(UserAccount.class, response).getBody();
	        return new RestResponse<UserAccount>(UserAccount.class, response);
	    }	    
}