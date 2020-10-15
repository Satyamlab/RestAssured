package apiEngine.endPoints;

import Base.Base;

public class Route {
	 
    private static final String BOOKSTORE = "/BookStore";
    private static final String ACCOUNT = "/Account";
    private static String VERSION;
   
    public Route(){
    	if(System.getenv("ENV")==null) {
    		VERSION = Base.Property("Version");;
    	}
    	else {
    		VERSION = System.getenv("ENV");
    	}
    }
    
    public static String generateToken(){
    	return ACCOUNT + VERSION + "/GenerateToken";
    }
 
    public static String books(){ 
    	return BOOKSTORE + VERSION + "/Books";
    }
 
    public static String book(){ 
    	return BOOKSTORE + VERSION + "/Book";
    }
 
    public static String userAccount(String userId){
    	return ACCOUNT + VERSION + "/User" + "/" + userId; 
    } 
}