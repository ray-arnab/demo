package com.company.demo.intg.customer;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CustomerControllerIT3 extends BaseIT {

    // TODO: Setup : delete all existing customers
    
    @Test
    public void getCustomers_afterCreation() throws Exception {
    	given().
        when().
            get(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.GET).
        then().
        	contentType(ContentType.APPLICATION_JSON.toString()).
        	statusCode(HttpStatus.OK.value()).
            assertThat().body("$", hasSize(CustomerControllerSuiteIT.INITIAL_NO_OF_CUSTOMERS + 1));
    	
    	//TODO: Also check one of the items is the customer created
    }
}