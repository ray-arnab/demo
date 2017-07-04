package com.company.demo.intg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class CustomControllerIT extends BaseIT {

    private static final String CUST_PATH = "/customer";

    
    @Test
    public void getCustomers() throws Exception {
        webDriver.get(getApiUrl() + CUST_PATH + "/");
//        String expectedJson = "[{\"id\":1,\"name\":\"test1\"}," + "{\"id\":2,\"name\":\"test2\"}]";
    }
}