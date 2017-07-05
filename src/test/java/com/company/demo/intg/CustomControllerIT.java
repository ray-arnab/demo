package com.company.demo.intg;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomControllerIT extends BaseIT {

    private static final String CUST_PATH = "/customer";
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    
    @Test
    public void getCustomers() throws Exception {
        webDriver.get(getApiUrl() + CUST_PATH + "/");
//        String expectedJson = "[{\"id\":1,\"name\":\"test1\"}," + "{\"id\":2,\"name\":\"test2\"}]";
//        WebElement element = webDriver.findElement(By.className("data"));
//        Assert.assertThat(json.getText(), is(equalTo(expectedJson)));
//        logger.log(Level.INFO, " >>>>>>>>>>>>>>>>>>>>>>>>>  " + webDriver.getPageSource());
//        logger.log(Level.INFO, " >>>>>>>>>>>>>>>>>>>>>>>>>  " + element.getText());
    }
}