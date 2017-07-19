package com.company.demo.intg.customer;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;

import static io.restassured.RestAssured.*;

public class CustomerControllerIT10 extends BaseIT {

    @Test
    public void deleteCustomer_existing() throws Exception {
    	given().
        when().
            delete(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.DELETE + "/" + CustomerControllerSuiteIT.customer.getId()).
        then().
        	statusCode(HttpStatus.OK.value());
            
    	//TODO: Assert body is blank
    }

}