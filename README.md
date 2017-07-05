# Springboots Demo


This is a standalone Springboots application which demonstrates the following:

1. A E2E Rest API for a given entity (say, 'Customer') with minimal attributes (say, id and name)
 
2. Supports CRUD with an underlying layer abstracted from the consumer of this REST API
	- Assumption: This API suite in turn communicates with a back-end REST layer

3. Able to switch between different implementations of CustomerService, depending on Spring profile, to source the data either from a in-memory store or from back-end APIs
	- This is useful for integration tests in dev environment, while relying on actual back-end urls for integration tests in higher envs
	
3. Unit testing of the CRUD methods

4. Swagger documentation for the API

5. Provision to version the API suite containing one or more of such APIs
	
6. Provision to setup integration test using Selenium across various environments (typically dev, build)

7. Secured by Oauth with a decoupled Authorization Server (see: https://github.com/ray-arnab/springboot-oauth-authzserver)



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



## OAuth nosecure mode
A Spring profile called 'nosecure' is provided which can be used in conjunction with other profiles to disable security.



## Start a stand alone application
mvn spring-boot:run -Dspring.profiles.active=env,nosecure
where env is dev|intg|uat|staging|prod


### This returns a html saying 'It works'

http://localhost:8080/api/v1/

	
### This returns a JSON showing 2 customer records (when run in dev mode)

http://localhost:8080/api/v1/customer/


Note: This endpoints will not be accessible if the application is run without the 'nosecure' option.



## Unit tests
mvn clean test -Dspring.profiles.active=env,nosecure

Assumption: We test the API responses minus the security



## Integration tests 
mvn clean verify -Dspring.profiles.active=dev,nosecure

Note: The application starts a server at random port against which the tests are run

### Prerequisites

1. Setup Selenium stand alone v 3.4.0 in local and start

java -jar selenium-server-standalone-3.4.0.jar -port 4445

2. Firefox should be installed

3. Download and run the OAuth Auhorization Server (https://github.com/ray-arnab/springboot-oauth-authzserver)



## Create package 
mvn package




## Swagger docs (from the standalone application)
http://localhost:8080/api/v1/swagger-ui.html
