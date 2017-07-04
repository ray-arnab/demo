package com.company.demo.intg;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=ITConfig.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("intg")
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
}