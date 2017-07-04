# demo
Demo


## Purpose

This is a standalone Springboots application which demonstrates the following:

1. A E2E Rest API for a given entity (say, 'Customer') with minimal attributes (say, id and name) 
2. It is to support CRUD with an underlying layer abstracted from the consumer of this REST API
	- this standalone application creates a memory based data store for Customers 
3. Automated unit testing of the CRUD methods
4. Swagger documentation for the API
5. Provision to version the API suite containing one or more of such APIs	
6. Provision to setup integration test using Selenium


## Use cases considered here are simple:

1. Get All Customers
	- a GET request without any parameter fetches details of all customers
2. Get Customer By Id
	- a GET request by ID fetches specific customer or responds with error if it is non existent
3. Add new customer 
	- Accepts POST request with name as parameter
	- No specific validation, and a new ID is uniquely generated
4. Update customer 
	- Accepts PUT request with JSON body containing id and name
	- Matches by ID of the customer, and updates if existing, responds with error if non existing
5. Delete customer by ID
	- Accepts DELETE request with id as param
	- Deletes if customer is existing, responds with error if non existing


## Start a stand alone application
mvn spring-boot:run

### This returns a html saying 'It works'

http://localhost:8080/api/v1/

	
### This returns a JSON showing 2 customer records

http://localhost:8080/api/v1/customer/



## Unit tests
mvn clean test


## Integration tests 
mvn clean verify

### Prerequisites

1. The command above starts a stand alone server at 8080. Please ensure that no other standalone application runs on that port at that stage

2. Setup Selenium stand alone v 3.4.0 in local and start

java -jar selenium-server-standalone-3.4.0.jar -port 4445



## Create package 
mvn package


## Swagger docs (from the standalone application)
http://localhost:8080/api/v1/swagger-ui.html