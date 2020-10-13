package stepDefination;

import org.testng.Assert;

import apiEngine.endPoints.EndPoints;
import apiEngine.endPoints.IRestResponse;
import apiEngine.model.Book;
import apiEngine.model.request.AddListOfBooks;
import apiEngine.model.request.AuthorizationRequest;
import apiEngine.model.request.CollectionOfIsbn;
import apiEngine.model.request.DeleteBook;
import apiEngine.model.response.Books;
import apiEngine.model.response.Token;
import apiEngine.model.response.UserAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class defination {
	 private static final String USER_ID = "f546a886-9f1e-4420-addd-713f38313adc";
	 private static final String USERNAME = "TOOLSQA-Satyam";
	 private static final String PASSWORD = "Test@@123";
	 private final String BaseUrl = "https://bookstore.toolsqa.com";
	 private static Response response;
	 private Book book;
	 private static IRestResponse<UserAccount> userAccountResponse;
	 private EndPoints endPoints;
	 
	@Given("I am an authorise user")
	public void i_am_an_authorise_user() {
		endPoints = new EndPoints(BaseUrl);
		AuthorizationRequest authRequest = new AuthorizationRequest(USERNAME,PASSWORD);
		endPoints.authenticateUser(authRequest);
	}

	@Given("A list of book are available")
	public void a_list_of_book_are_available() {
    	IRestResponse<Books> booksResponse = endPoints.getBooks();
    	book = booksResponse.getBody().books.get(0);
    }

	@When("I add a book to my reading list")
	public void i_add_a_book_to_my_reading_list() {

        CollectionOfIsbn isbn = new CollectionOfIsbn(book.isbn);
        AddListOfBooks addBooksRequest = new AddListOfBooks(USER_ID, isbn);
        userAccountResponse = endPoints.addBook(addBooksRequest);
	}

	@Then("The book is added")
	public void the_book_is_added() {
		Assert.assertEquals(201, userAccountResponse.getStatusCode());
    	userAccountResponse = endPoints.getUserAccount(USER_ID);
		Assert.assertEquals(USER_ID, userAccountResponse.getBody().userId); 
		Assert.assertEquals(book.isbn, userAccountResponse.getBody().books.get(0).isbn);
	}

	@When("I remove a book from my reading list")
	public void i_remove_a_book_from_my_reading_list() {
		DeleteBook deleteBook = new DeleteBook(book.isbn, USER_ID);
		response = endPoints.removeBook(deleteBook);
	}
	
	@Then("The book is removed")
	public void the_book_is_removed() {
	     Assert.assertEquals(204, response.getStatusCode());
	     
	     userAccountResponse = endPoints.getUserAccount(USER_ID);
	     Assert.assertEquals(200, userAccountResponse.getStatusCode());
	     Assert.assertEquals(0, userAccountResponse.getBody().books.size());
	}	
}