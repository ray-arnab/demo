package com.company.demo.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.company.demo.model.Customer;
import com.company.demo.service.CustomerService;

@Profile({"default","intg","uat","staging","prod"})
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${api.customer.get}")
	private String getUrl;
	
	@Value("${api.customer.getAll}")
	private String getAllUrl;

//    public CustomerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

    
	@Override
	public boolean isExisting(long id) {
		return false;
	}

	@Override
	public List<Customer> getAll() {
        List<Customer> customerList = restTemplate.getForObject(getAllUrl, List.class);
        
        // Process response if required
        return customerList;
	}

	@Override
	public Customer get(long id) {
		Customer customer = restTemplate.getForObject("", Customer.class, id);
		return customer;
	}

	@Override
	public Customer create(String name) {
		Customer customer = restTemplate.getForObject("", Customer.class, name);
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		Customer customer1 = restTemplate.getForObject("", Customer.class, customer);
		return customer1;
	}

	@Override
	public void remove(long id) {
		restTemplate.getForObject("",Void.class, id);
	}

	
}
