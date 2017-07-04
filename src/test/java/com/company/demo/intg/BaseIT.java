package com.company.demo.intg;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfig.class)
public abstract class BaseIT {

	@Autowired
    protected WebDriver webDriver;
	
	@Value("${server.contextPath}")
	private String contextPath;
   
	@Value("${api.instance}")
	private String apiInstance;
	
	/**
	 * Method returns the API URL along with the context path
	 * eg: http://host:port/api/v1
	 * @return
	 */
	protected final String getApiUrl(){
		return apiInstance + contextPath;
	}
}