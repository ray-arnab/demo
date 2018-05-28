package com.company.demo.intg.customer;


import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;
import com.company.demo.model.Customer;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CustomerControllerIT7 extends BaseIT {

    @Test
    public void updateCustomer_existing() throws Exception {
    	Customer updateCustomer = new Customer();
    	updateCustomer.setId(CustomerControllerSuiteIT.customer.getId());
    	updateCustomer.setName("Jonathan Doe1");
    	
    	given().
	       contentType("application/json; charset=UTF-8").
	       body(updateCustomer).
		when().
			put(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.UPDATE).
		then().
			contentType(ContentType.APPLICATION_JSON.toString()).
			statusCode(HttpStatus.OK.value()).
			assertThat().body("id", equalTo(CustomerControllerSuiteIT.customer.getId().intValue())).
            assertThat().body("name", equalTo("Jonathan Doe1"));
    }
    
}