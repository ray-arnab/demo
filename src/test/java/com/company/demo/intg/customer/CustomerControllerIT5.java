package com.company.demo.intg.customer;


import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;

import static io.restassured.RestAssured.*;

public class CustomerControllerIT5 extends BaseIT {
    @Test
    public void getCustomer_nonExisting() throws Exception {
    	given().
        when().
            get(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.GET + (CustomerControllerSuiteIT.customer.getId()-1)).
        then().
        	statusCode(HttpStatus.NOT_FOUND.value());
       //     assertThat().body("$", hasSize(1));
    }

}