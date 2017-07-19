package com.company.demo.intg.customer;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CustomerControllerIT8 extends BaseIT {
    
    @Test
    public void getCustomer_validateUpdate() throws Exception {
    	given().
        when().
            get(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.GET + CustomerControllerSuiteIT.customer.getId()).
        then().
        	contentType(ContentType.APPLICATION_JSON.toString()).
        	statusCode(HttpStatus.OK.value()).
            assertThat().body("id", equalTo(CustomerControllerSuiteIT.customer.getId().intValue())).
            assertThat().body("name", equalTo("Jonathan Doe1"));
    }
    
}