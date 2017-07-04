package com.company.demo.intg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfig.class)
public class IndexIT {

    @Autowired
    private WebDriver webDriver;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void visitIndexPage() throws Exception {

        webDriver.get(ITConfig.API_HOST + "/api/v1");
        WebElement working = webDriver.findElement(By.className("working"));

        Assert.assertThat(working.getText(), is(equalTo("It works!")));
    }
}