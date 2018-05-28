package com.company.demo.intg.customer;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;
import com.company.demo.model.Customer;

import static io.restassured.RestAssured.*;

public class CustomerControllerIT6 extends BaseIT {
    @Test
    public void updateCustomer_nonExisting() throws Exception {
    	
    	Customer updateCustomer = new Customer();
    	updateCustomer.setId(CustomerControllerSuiteIT.customer.getId() - 1);
    	updateCustomer.setName("Jonathan Doe1");
    	
    	given().
    	       contentType("application/json; charset=UTF-8").
    	       body(updateCustomer).
    	when().
    		put(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.UPDATE).
    	then().
    		statusCode(HttpStatus.NOT_FOUND.value());
    }
    
}