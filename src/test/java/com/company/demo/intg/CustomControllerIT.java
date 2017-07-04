package com.company.demo.intg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfig.class)
public class CustomControllerIT {

	@Autowired
    protected WebDriver webDriver;
	
    private static final String CUST_PATH = "/customer";

    
    @Test
    public void getCustomers() throws Exception {
        //webDriver.get(ITConfig.API_HOST + contextPath + CUST_PATH);
        webDriver.get(ITConfig.API_HOST + "/api/v1" + CUST_PATH + "/");
//        String expectedJson = "[{\"id\":1,\"name\":\"test1\"}," + "{\"id\":2,\"name\":\"test2\"}]";
    }
}