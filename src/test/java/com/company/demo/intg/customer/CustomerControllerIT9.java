package com.company.demo.intg.customer;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;

import static io.restassured.RestAssured.*;

public class CustomerControllerIT9 extends BaseIT {

   
    @Test
    public void deleteCustomer_nonExisting() throws Exception {
    	long wrongId = CustomerControllerSuiteIT.customer.getId() - 1;
    	
    	given().
        when().
            delete(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.DELETE + "/" + wrongId).
        then().
			statusCode(HttpStatus.NOT_FOUND.value());
    }
    
}