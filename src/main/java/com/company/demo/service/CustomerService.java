package com.company.demo.service;

import java.util.List;
import com.company.demo.model.Customer;

public interface CustomerService {

	public boolean isExisting(long id) ;

	public List<Customer> getAll() ;

	public Customer get(long id) ;

	public Customer create(String name) ;

	public Customer update(Customer customer) ;

	public void remove(long id) ;

}
