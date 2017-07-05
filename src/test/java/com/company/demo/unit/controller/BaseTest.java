package com.company.demo.unit.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.company.demo.controller.CustomerController;
import com.company.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public abstract class BaseTest {

	@Autowired
	protected MockMvc mockMvc;

	
	// Define bean and relative URI for customerService
	@MockBean
	protected CustomerService customerService;
	
	protected static String URL = "/customer/";
	
	
	// Likewise for other services

}
