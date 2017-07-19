package com.company.demo.intg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.company.demo.intg.customer.CustomerControllerIT1;
import com.company.demo.intg.customer.CustomerControllerIT10;
import com.company.demo.intg.customer.CustomerControllerIT11;
import com.company.demo.intg.customer.CustomerControllerIT12;
import com.company.demo.intg.customer.CustomerControllerIT2;
import com.company.demo.intg.customer.CustomerControllerIT3;
import com.company.demo.intg.customer.CustomerControllerIT4;
import com.company.demo.intg.customer.CustomerControllerIT5;
import com.company.demo.intg.customer.CustomerControllerIT6;
import com.company.demo.intg.customer.CustomerControllerIT7;
import com.company.demo.intg.customer.CustomerControllerIT8;
import com.company.demo.intg.customer.CustomerControllerIT9;
import com.company.demo.model.Customer;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CustomerControllerIT1.class, 
    CustomerControllerIT2.class,
    CustomerControllerIT3.class,
    CustomerControllerIT4.class,
    CustomerControllerIT5.class,
    CustomerControllerIT6.class,
    CustomerControllerIT7.class,
    CustomerControllerIT8.class,
    CustomerControllerIT9.class,
    CustomerControllerIT10.class,
    CustomerControllerIT11.class,
    CustomerControllerIT12.class
})
public class CustomerControllerSuiteIT {
	public static final String CUST_PATH = "/customer";
    
    public static final String GET = "/";
    public static final String ADD = "/add";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    
    public static final int INITIAL_NO_OF_CUSTOMERS = 2;
    
    public static Customer customer;
    
}
