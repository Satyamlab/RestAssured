package apiEngine.model.request;

public class DeleteBook {

public String isbn;
public String userId;

/**
* No args constructor for use in serialization
*
*/
public DeleteBook() {
}

/**
*
* @param isbn
* @param userId
*/
public DeleteBook(String isbn, String userId) {
super();
this.isbn = isbn;
this.userId = userId;
}

}