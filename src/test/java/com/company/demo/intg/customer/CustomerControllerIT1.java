package com.company.demo.intg.customer;

import java.util.logging.Logger;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CustomerControllerIT1 extends BaseIT {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    // TODO: Setup : delete all existing customers
    
    
    @Test()
    public void getCustomers_beforeCreation() throws Exception {
    	given().
        when().
            get(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.GET).
        then().
        	contentType(ContentType.APPLICATION_JSON.toString()).
        	statusCode(HttpStatus.OK.value()).
            assertThat().body("$", hasSize(CustomerControllerSuiteIT.INITIAL_NO_OF_CUSTOMERS));
    }
 
}