package com.company.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.company.demo.model.Customer;
import com.company.demo.service.CustomerService;

@Profile("dev")
@Service
public class CustomerServiceLocalImpl implements CustomerService {

	private static final Logger logger = Logger.getLogger(CustomerServiceLocalImpl.class.getName());
	
	private static Map<Long, Customer> customerMap;
	
	static {
		
		customerMap = new HashMap<Long, Customer>();

		Customer cust1 = new Customer(ThreadLocalRandom.current().nextLong(1L, Long.MAX_VALUE), "John Doe");
		customerMap.put(cust1.getId(), cust1);
		logger.log(Level.INFO, "Cust 1 : " + cust1);

		Customer cust2 = new Customer(ThreadLocalRandom.current().nextLong(1L, Long.MAX_VALUE), "Jane Doe");
		customerMap.put(cust2.getId(), cust2);
		logger.log(Level.INFO, "Cust 2 : " + cust2);

	}

	public boolean isExisting(long id) {

		if (customerMap.containsKey(id)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Customer> getAll() {
		return new ArrayList<Customer>(customerMap.values());
	}

	public Customer get(long id) {
		return customerMap.get(id);
	}

	public Customer create(String name) {
		Customer customer = new Customer(ThreadLocalRandom.current().nextLong(1L, Long.MAX_VALUE), name);
		customerMap.put(customer.getId(), customer);
		return customer;
	}

	public Customer update(Customer customer) {
		customerMap.put(customer.getId(), customer);
		return customer;
	}

	public void remove(long id) {
		customerMap.remove(id);
	}

	
//	public callToBackEnd(){
//		String URL="http://local.company.com/..";
//	    RestTemplate template=new RestTemplate();
//	 
//	  	Customer customer = template.getForObject(URL,Customer.class);
//	    return customer;
//	}
}
