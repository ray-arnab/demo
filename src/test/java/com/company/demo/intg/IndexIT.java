package com.company.demo.intg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class IndexIT extends BaseIT{
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void visitIndexPage() throws Exception {

        webDriver.get(getApiUrl());
        
        WebElement working = webDriver.findElement(By.className("working"));

        Assert.assertThat(working.getText(), is(equalTo("It works!")));
    }
}