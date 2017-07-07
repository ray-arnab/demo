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

<pre>
mvn spring-boot:run -Dspring.profiles.active=env,nosecure
</pre>

where env is dev|intg|uat|staging|prod


### This returns a html saying 'It works'

<pre>
http://localhost:8080/api/v1/
</pre>
	
### This returns a JSON showing 2 customer records (when run in dev mode)

<pre>
http://localhost:8080/api/v1/customer/
</pre>


Note: This endpoints will not be accessible if the application is run without the 'nosecure' option.



## Unit tests

<pre>
mvn clean test -Dspring.profiles.active=nosecure
</pre>

Assumption: We test the API responses minus the security



## Integration tests 

<pre>
mvn clean verify -Dspring.profiles.active=dev,nosecure
</pre>

Note: The application starts a server at random port against which the tests are run

### Prerequisites

1. Setup Selenium stand alone v 3.4.0 in local and start

<pre>
java -jar selenium-server-standalone-3.4.0.jar -port 4445
</pre>

2. Firefox should be installed



## Create package 

<pre>
mvn package
</pre>



## Swagger docs (from the standalone application)

<pre>
http://localhost:8080/api/v1/swagger-ui.html
</pre>



## Oauth client

### Purpose

- It is to demonstrate how a OAuth protected API (like those provided in this application) can be consumed from the client (browser) side.

- A sample 'client application' is packaged in this API (server) application so as to bypass CORS issue in this simple standalone setup. Normally it would be a different application.


### Test it

- Ensure the Oauth authorization server is running (https://github.com/ray-arnab/springboot-oauth-authzserver)

- Start this application in 'dev' mode (without 'nosecure').

- Try accessing the getCustomers API (http://localhost:8080/api/v1/customers/) directly in a browser. Access will be denied.

- Now access http://localhost:8080/api/v1/client/lookup.html. Using AJAX, it fetches all the customers accessing the above API


