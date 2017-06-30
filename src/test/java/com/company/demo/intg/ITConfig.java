package com.company.demo.intg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class ITConfig {

    @Autowired
    private Environment env;
    
    //TODO: Make use of these
//    @Value("${server.contextPath}")
//    private String contextPath;
//    
//    @Value("${server.port}")
//    private int port;
    
    
    public static final String WD_URL = "http://localhost:4445/wd/hub";
    public static final String API_HOST = "http://localhost:8080";

    @Bean
    public WebDriver webDriver() throws MalformedURLException {
        return new RemoteWebDriver(getRemoteUrl(), getDesiredCapabilities());
    }

    private DesiredCapabilities getDesiredCapabilities() {
        return DesiredCapabilities.firefox();
    }

    private URL getRemoteUrl() throws MalformedURLException {
        return new URL(WD_URL);
    }
}