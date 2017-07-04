package com.company.demo.intg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@PropertySource(value = { "classpath:application.properties"})
public class ITConfig {

    @Autowired
    private Environment env;
    
    public static final String PROP_WD_URL = "selenium.wd.url";

    @Bean
    public WebDriver webDriver() throws MalformedURLException {
    	String url = env.getProperty(PROP_WD_URL);
        return new RemoteWebDriver(getRemoteUrl(url), getDesiredCapabilities());
    }
    
    private DesiredCapabilities getDesiredCapabilities() {
        return DesiredCapabilities.firefox();
    }

    private URL getRemoteUrl(String url) throws MalformedURLException {
        return new URL(url);
    }
    
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}