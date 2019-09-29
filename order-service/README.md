# Live Order Board 
Implementation for the Silver Bars Marketplace live order board.

# The Brief
Create an implementation for a live order board allowing users to register, cancel and view a summary of orders.

# Assumptions
During this exercise I've made the following assumptions:
 - There should be nothing output to the order board when an order is registered or cancelled.
 - To distinguish between the BUY and SELL orders on the summary, a heading is also output to provide categorisation.

Note: On a real project, such questions would be discussed with the product owner to ensure the output was as they desired. 

 - an Order object is equal if all the properties are the same. In real life, each order would probably additionally have a unique id.

# To Run
- clone the repository:
```
git clone git@github.com:gemcfadyen/silverBarsMarketplace.git
```
- Navigate to the root of the project:
```
cd order-service
```

- Using the maven build the program and run the tests:
```
mvn install 
```

In order to demonstrate the application, I created a small demo app which summarises orders that have been registered and cancelled.

- To run the demo app:
```
mvn package

java -cp target/order-service-1.0.jar com.marketplace.silver.bars.LiveOrderBoardApp

```
_Alternatively you can invoke the main method of the LiveOrderBoardApp class in your IDE._

