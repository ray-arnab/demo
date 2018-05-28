package com.company.demo.intg.customer;


import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;
import com.company.demo.model.Customer;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

public class CustomerControllerIT2 extends BaseIT {

    // TODO: Setup : delete all existing customers
    
    @Test
    public void createCustomer() throws Exception {
    	Response response =
    	given().
        	parameters("name", "Jonathan Doe").
        when().
        	post(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.ADD).
        then().
        	contentType(ContentType.APPLICATION_JSON.toString()).
        	statusCode(HttpStatus.CREATED.value()).
    		body("name", equalTo("Jonathan Doe")).
    	extract().
            response();
    	
    	CustomerControllerSuiteIT.customer = response.getBody().as(Customer.class);
    	assertNotNull("Customer object could not be created", CustomerControllerSuiteIT.customer);
    }
    
}