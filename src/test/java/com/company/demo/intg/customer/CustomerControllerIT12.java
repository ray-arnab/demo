package com.company.demo.intg.customer;

import org.junit.Test;

import com.company.demo.intg.BaseIT;
import com.company.demo.intg.CustomerControllerSuiteIT;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CustomerControllerIT12 extends BaseIT {


    @Test
    public void getCustomers_afterDeletion() throws Exception {
    	given().
        when().
            get(CustomerControllerSuiteIT.CUST_PATH + CustomerControllerSuiteIT.GET).
        then().
            assertThat().body("$", hasSize(CustomerControllerSuiteIT.INITIAL_NO_OF_CUSTOMERS));
    }
}