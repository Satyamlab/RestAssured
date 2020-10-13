Feature: End to End Tests for 
Description : The purpose of these test are to cover End to End happy flows for customer

Book Store Swagger URL: http://bookstore.toolsqa.com/swagger/index.html

	Background: User generates token for Authorisation
		Given I am an authorise user
		
	Scenario: Authorise user is able to Add and Remove a book
		Given A list of book are available
		When I add a book to my reading list
		Then The book is added
		When I remove a book from my reading list
		Then The book is removed