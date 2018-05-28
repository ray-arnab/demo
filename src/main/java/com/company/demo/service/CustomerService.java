package com.company.demo.service;

import java.util.List;

import com.company.demo.exceptions.ApplicationException;
import com.company.demo.model.Customer;

public interface CustomerService {

	public boolean isExisting(long id) throws ApplicationException;

	public List<Customer> getAll() throws ApplicationException;

	public Customer get(long id) throws ApplicationException;

	public Customer create(String name) throws ApplicationException;

	public Customer update(Customer customer) throws ApplicationException;

	public void remove(long id) throws ApplicationException;

}
