package com.company.demo.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.exceptions.ApplicationException;
import com.company.demo.model.Customer;
import com.company.demo.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value="/customer")
@Api(value="customerApi", description="Operations pertaining to customer entity")
public class CustomerController  {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "Get all customers", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list")
	}
	)
	@RequestMapping(value="/",method = RequestMethod.GET)
	public List<Customer> getAllCustomers(){
		logger.log(Level.FINE, "Start");
		
		List<Customer> customers = customerService.getAll();
		logger.log(Level.FINE, "Received customer list");

		return customers;
	};
	
	
	
	@ApiOperation(value = "Get a customer by ID", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved customer"),
	        @ApiResponse(code = 500, message = "Customer does not exist")
	}
	)
	@RequestMapping(value="/{id}",method = RequestMethod.GET,produces = "application/json")
	public Customer getCustomer(@PathVariable Long id) throws ApplicationException {
		logger.log(Level.FINE, "Start");
		
		Customer customer = customerService.get(id);
		logger.log(Level.FINE, "Retrieved customer {0}", id);
		
		if(customer == null) {
			logger.log(Level.INFO, "Non existing customer {0}", id);
			throw new ApplicationException("There is no customer with this id");
		}
		
		return customer;
	}
	
	
	@ApiOperation(value = "Add a customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved customer")
	}
	)
	@RequestMapping(value="/add",method = RequestMethod.POST,produces = "application/json")
	public Customer addCustomer(@RequestParam(value="name") String name) throws ApplicationException{
		logger.log(Level.FINE, "Start");
		
		Customer customer = customerService.create(name);
		logger.log(Level.FINE, "Created customer with id {0}", customer.getId());
		
		return customer;
	}
	
	
	@ApiOperation(value = "Update a customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved customer"),
	        @ApiResponse(code = 500, message = "Customer does not exist")
	}
	)
	@RequestMapping(value="/update",method = RequestMethod.PUT,  produces = "application/json")
	public Customer updateCustomer(@RequestBody Customer customer) throws ApplicationException {
		logger.log(Level.FINE, "Start");
		
		if(customer == null || customer.getId() == null) {
			throw new ApplicationException("Customer ID is missing in request");
		}
	 
		if(customerService.isExisting(customer.getId())){
			logger.log(Level.FINE, "Existing customer {0}", customer.getId());

		   Customer updatedCustomer = customerService.update(customer);
		   logger.log(Level.FINE, "Updated customer {0}", customer.getId());

		   return updatedCustomer;
	   } else {
		   logger.log(Level.INFO, "Non existing customer {0}", customer.getId());
		   throw new ApplicationException("Customer " + customer.getId() + " does not exist");
	   }
	 
	}
	
	@ApiOperation(value = "Delete a customer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved customer"),
	        @ApiResponse(code = 500, message = "Customer does not exist")
	}
	)
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable Long id) throws ApplicationException {
		logger.log(Level.FINE, "Start");
		
		if(customerService.isExisting(id)) {
			logger.log(Level.FINE, "Existing customer {0}", id);
			customerService.remove(id);
			logger.log(Level.FINE, "Removed customer {0}", id);
		} else {
			logger.log(Level.INFO, "Non existing customer {0}", id);
			throw new ApplicationException("Customer " + id + " does not exist");
		}
	}
}
