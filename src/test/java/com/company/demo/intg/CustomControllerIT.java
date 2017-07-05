package com.company.demo.intg;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class CustomControllerIT extends BaseIT {

    private static final String CUST_PATH = "/customer";
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    
    @Test
    public void getCustomers() throws Exception {
        webDriver.get(getApiUrl() + CUST_PATH + "/");
//        String expectedJson = "[{\"id\":1,\"name\":\"test1\"}," + "{\"id\":2,\"name\":\"test2\"}]";
//        WebElement json = webDriver.findElement(By.id("json"));
//        Assert.assertThat(json.getText(), is(equalTo(expectedJson)));
        logger.log(Level.INFO, " >>>>>>>>>>>>>>>>>>>>>>>>>  " + webDriver.getPageSource());
    }
}