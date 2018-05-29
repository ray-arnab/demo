package com.company.demo.client;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * This controller class is created to test a few concepts of Oauth security
 * Typically the client artifact should belong in a different project.
 * It (along with other client artifacts in resources) has been included to bypass the CORS issue for this quick POC
 * 
 * @author aray5
 *
 */

@Controller
@RequestMapping(value="/client")
public class ClientController  {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
//	private static final String TOKEN_URL = "http://localhost:8081/auth/oauth/token";
	
	private static MultiValueMap<String, String> requestBody;
	private static MultiValueMap<String, String> requestHeaders;
	private static HttpEntity<?> httpEntity;
	
	
	static {
		requestBody = new LinkedMultiValueMap<String, String>();
		requestBody.add("grant_type", "client_credentials");
		
		requestHeaders = new LinkedMultiValueMap<String, String>();
		requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		requestHeaders.add("Authorization", "Basic Y2xpZW50MjpwYXNzd29yZDI=");
		
		httpEntity = new HttpEntity<Object>(requestBody, requestHeaders);
	}
	     

	@Value("${oauth.token.url}")
	private String tokenUrl;
	
	@RequestMapping("/fetchToken")
    public String fetchToken(Model model) {
		
        RestTemplate restTemplate = new RestTemplate();
        OauthToken oauthToken = restTemplate.postForObject(tokenUrl, httpEntity, OauthToken.class);
        logger.info(oauthToken.toString());
		
		String token = oauthToken.getAccess_token();
		long expiry = oauthToken.getExpires_in();
        model.addAttribute("token", token);
        model.addAttribute("expiry", expiry);
        
     // point to the relative path under resources/templates
        return "client/fetchToken";	
    }
}
