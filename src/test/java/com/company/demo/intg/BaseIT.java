package com.company.demo.intg;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {
	
	@Autowired
    protected WebDriver webDriver;
	
	@Value("${server.contextPath}")
	private String contextPath;
   
	@LocalServerPort
    private int port;
	
	/**
	 * Method returns the API URL along with the context path
	 * eg: http://host:port/api/v1
	 * @return
	 */
	protected final String getApiUrl(){
		return "http://localhost:" + port + contextPath;
	}
	
	@Before
	public void setup() {
	    RestAssured.baseURI = "http://localhost";
	    RestAssured.port = port;
	    RestAssured.basePath = contextPath;
	}
	
}