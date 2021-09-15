# p1-TerryLight

> ## Project 1:Lemonade Stand application
Restful API for a creating a simple lemonade stand that stores a customers id, name, drink, price total.
Each order will decrease the amount of stock, so it sohuld restock form a supplier at a cost.
Users may choose additional toppings for added charge.
At the end of the day, it should provide a list of the expenses made and save it.

> ## Technologies
> - Java 8+
> - Maven
> - slf4j
> - Spring Framework
> - Cassandra DB
> - Reactor Netty

> ## User stories
Store:
Store can check who's on the waiting list

Store can update the customers name, order, and price

Customer:
Customer can put their name and order down


> ## Usage
Compile
To compile the program use: mvn compile

Clean
To clean up artifacts created by prior builds:
mvn clean

> ## RESTful API endpoints
> - GET /orders/{id}: Retrieves the user information based on the given account ID
> - DELETE orders/{id}: Removes a user from the database based on the given account ID if the account balance is 0
> - POST orders: Adds a new order to the database


> ## Future Work
Work on testing

Set endpoints to update user information
Improve current endpoints
