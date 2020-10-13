package apiEngine.model.request;
import java.util.ArrayList;
import java.util.List;

public class AddListOfBooks {

public String userId;
public List<CollectionOfIsbn> collectionOfIsbns = null;

/**
* No args constructor for use in serialization
*
*/
public AddListOfBooks() {
}

/**
*
* @param collectionOfIsbns
* @param userId
*/
public AddListOfBooks(String userId, CollectionOfIsbn ISBN) {
super();
this.userId = userId;
collectionOfIsbns = new ArrayList<CollectionOfIsbn>();
collectionOfIsbns.add(ISBN);
}

}