package com.company.demo.unit.controller;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.demo.model.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

public class CustomerControllerTest extends BaseTest {


	private List<Customer> buildCustomers() {
		Customer c1 = new Customer(1l, "test1");
		Customer c2 = new Customer(2l, "test2");
		List<Customer> custList = Arrays.asList(c1, c2);
		return custList;
	}

	private void verifyResponseAndCode(MockHttpServletResponse response, int code) throws Exception {
		assertNotNull("Response Stream is null", response);

		// Verify Response code
		int status = response.getStatus();
		assertEquals("Incorrect Response Status", code, status);
	}
	
	@Before
	public void setup(){
	}

	// **********************************************
	// ** GetAllCustomers
	// **********************************************

	@Test
	public void getAllCustomersTest() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);

		// Execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.OK.value());

		// Verify that service method was called once
		verify(customerService).getAll();

		// Verify JSON structure and values
		assertNotNull("Response Content is null", response.getContentAsString());
		String expectedJson = "[{\"id\":1,\"name\":\"test1\"}," + "{\"id\":2,\"name\":\"test2\"}]";
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}

	@Test
	public void getAllCustomersTest_emptySet() throws Exception {

		// Execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.OK.value());

		// Verify that service method was called once
		verify(customerService).getAll();

		// Verify JSON structure and values
		assertNotNull("Response Content is null", response.getContentAsString());
		String expectedJson = "[]";
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}

	@Test
	public void getAllCustomersTest_unsupportedMethods() throws Exception {

		// Execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.METHOD_NOT_ALLOWED.value());

		// Execute
		MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.put(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// Verify Response status
		MockHttpServletResponse response1 = result1.getResponse();
		verifyResponseAndCode(response1, HttpStatus.METHOD_NOT_ALLOWED.value());

		// Execute
		MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.delete(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// Verify Response status
		MockHttpServletResponse response2 = result2.getResponse();
		verifyResponseAndCode(response2, HttpStatus.METHOD_NOT_ALLOWED.value());
	}

	
	
	
	
	// **********************************************
	// ** GetCustomer
	// **********************************************

	@Test
	public void getCustomerTest_exists() throws Exception {

		// Prepare and take the first customer
		List<Customer> custList = buildCustomers();
		when(customerService.get(any(Long.class))).thenReturn(custList.get(0));

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "/1").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.OK.value());

		// Verify that service method was called once
		verify(customerService).get(any(Long.class));

		// Verify JSON structure and values
		assertNotNull("Response Content is null", response.getContentAsString());
		String expectedJson = "{\"id\":1,\"name\":\"test1\"}";
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}

	@Test
	public void getCustomerTest_doesNotExist() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.get(any(Long.class))).thenReturn(null);
		
		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "/" + Long.MAX_VALUE).accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.NOT_FOUND.value());

		// Verify that service method was called once
		verify(customerService).get(any(Long.class));

		// TODO: Assert blank body?
	}
	
	@Test
	public void getCustomerTest_incorrectDataType() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.get(any(Long.class))).thenReturn(custList.get(0));

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL + "/methodname").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.METHOD_NOT_ALLOWED.value());	// since an incorrect data type for ID gets treated as a call to different method that does not exist
	}

	@Test
	public void getCustomerTest_unsupportedMethods() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.get(any(Long.class))).thenReturn(custList.get(0));

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL + "/1").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.METHOD_NOT_ALLOWED.value());

		// Execute
		MvcResult result1 = mockMvc
				.perform(MockMvcRequestBuilders.put(URL + "/1").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response1 = result1.getResponse();
		verifyResponseAndCode(response1, HttpStatus.METHOD_NOT_ALLOWED.value());

		// Execute
		MvcResult result2 = mockMvc
				.perform(MockMvcRequestBuilders.delete(URL + "/1").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response2 = result2.getResponse();
		verifyResponseAndCode(response2, HttpStatus.METHOD_NOT_ALLOWED.value());
	}
	
	
	
	

	// **********************************************
	// ** AddCustomer
	// **********************************************

	@Test
	public void addCustomerTest() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);
		
		Customer customer = new Customer(3L,"test3");
		when(customerService.create(any(String.class))).thenReturn(customer);
		

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL + "/add").param("name", "test3").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.CREATED.value());

		// Verify that service method was called once
		verify(customerService).create(any(String.class));

		// Verify JSON structure and values
		assertNotNull("Response Content is null", response.getContentAsString());
		String expectedJson = "{\"id\":3,\"name\":\"test3\"}";
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	
	
	@Test
	public void addCustomerTest_NameMissing() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);
		
		Customer customer = new Customer(3L,"test3");
		when(customerService.create(any(String.class))).thenReturn(customer);
		

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL + "/add").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		
		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void addCustomerTest_unsupportedMethods() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);
		
		Customer customer = new Customer(3L,"test3");
		when(customerService.create(any(String.class))).thenReturn(customer);
		
		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "/add").param("name", "test3").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.BAD_REQUEST.value());		// since the API takes 'add' as customer ID for the getCustomer method

		// Execute
		MvcResult result1 = mockMvc
				.perform(MockMvcRequestBuilders.put(URL + "/add").param("name", "test3").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response1 = result1.getResponse();
		verifyResponseAndCode(response1, HttpStatus.METHOD_NOT_ALLOWED.value());

		// Execute
		MvcResult result2 = mockMvc
				.perform(MockMvcRequestBuilders.delete(URL + "/add").param("name", "test3").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response2 = result2.getResponse();
		verifyResponseAndCode(response2, HttpStatus.METHOD_NOT_ALLOWED.value());

	}

	
	
	
	// **********************************************
	// ** UpdateCustomer
	// **********************************************

	@Test
	public void updateCustomerTest_exists() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);
		
		when(customerService.isExisting(any(Long.class))).thenReturn(true);
		
		Customer customer = new Customer(2l,"test3");
		when(customerService.update(any(Customer.class))).thenReturn(customer);
		
		
		// Execute
		String updateCustJson = "{\"id\":2,\"name\":\"test3\"}";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put(URL + "/update")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(updateCustJson)
						.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.OK.value());

		// Verify that service method was called once
		verify(customerService).update(any(Customer.class));

		// Verify JSON structure and values
		assertNotNull("Response Content is null", response.getContentAsString());
		String expectedJson = "{\"id\":2,\"name\":\"test3\"}";
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	
	@Test
	public void updateCustomerTest_doesNotExist() throws Exception {

		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);
		
		when(customerService.isExisting(any(Long.class))).thenReturn(false);
		
		Customer customer = new Customer(3l,"test3");
		when(customerService.update(any(Customer.class))).thenReturn(customer);
		
		
		// Execute
		String updateCustJson = "{\"id\":3,\"name\":\"test3\"}";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put(URL + "/update")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(updateCustJson)
						.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.NOT_FOUND.value());

		// Verify that service method was called once
		verify(customerService).isExisting(any(Long.class));

		// TODO: Verify JSON structure and values?
	}
	
	@Test
	public void updateCustomerTest_blankBody() throws Exception {
		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);
		
		when(customerService.isExisting(any(Long.class))).thenReturn(true);
		
		Customer customer = new Customer(2l,"test3");
		when(customerService.update(any(Customer.class))).thenReturn(customer);
		
		
		// Execute
		String updateCustJson = "{}";
		MvcResult result = null;
		try {
			result = mockMvc
					.perform(MockMvcRequestBuilders.put(URL + "/update")
							.contentType(MediaType.APPLICATION_JSON_UTF8)
							.content(updateCustJson)
							.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
			
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Customer ID is missing in request"));
		}
	}
	
	@Test
	public void updateCustomerTest_idMissingInBody() throws Exception {
		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.getAll()).thenReturn(custList);
		
		when(customerService.isExisting(any(Long.class))).thenReturn(true);
		
		Customer customer = new Customer(2l,"test3");
		when(customerService.update(any(Customer.class))).thenReturn(customer);
		
		
		// Execute
		String updateCustJson = "{\"name\":\"test3\"}";
		MvcResult result = null;
		try {
			result = mockMvc
					.perform(MockMvcRequestBuilders.put(URL + "/update")
							.contentType(MediaType.APPLICATION_JSON_UTF8)
							.content(updateCustJson)
							.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Customer ID is missing in request"));
		}
	}
	
	
	
	// **********************************************
	// ** DeleteCustomer
	// **********************************************

	@Test
	public void deleteCustomerTest_exists() throws Exception {
		
		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.get(any(Long.class))).thenReturn(custList.get(0));
		
		when(customerService.isExisting(any(Long.class))).thenReturn(true);
		
		//when(customerService.remove(any(Long.class))).thenReturn();

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete(URL + "/delete/1").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.OK.value());

		// Verify that service method was called once
		verify(customerService).remove(any(Long.class));

		// Verify JSON structure and values
		assertNotNull("Response Content is null", response.getContentAsString());
		assertTrue(response.getContentAsString().length() == 0);
	}
	
	@Test
	public void deleteCustomerTest_doesNotExist() throws Exception {
		
		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.get(any(Long.class))).thenReturn(custList.get(0));
		
		when(customerService.isExisting(any(Long.class))).thenReturn(false);
		
		//when(customerService.remove(any(Long.class))).thenReturn();

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete(URL + "/delete/5").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.NOT_FOUND.value());

		// Verify that service method was called once
		verify(customerService).isExisting(any(Long.class));

		// TODO: Verify JSON structure and values?
		
	}
	
	@Test
	public void deleteCustomerTest_idParamMissing() throws Exception {
		
		// Prepare
		List<Customer> custList = buildCustomers();
		when(customerService.get(any(Long.class))).thenReturn(custList.get(0));
		
		when(customerService.isExisting(any(Long.class))).thenReturn(true);
		
		//when(customerService.remove(any(Long.class))).thenReturn();

		// Execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete(URL + "/delete/").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// Verify Response status
		MockHttpServletResponse response = result.getResponse();
		verifyResponseAndCode(response, HttpStatus.METHOD_NOT_ALLOWED.value());

		// Verify that service method was called once
//		verify(customerService).remove(any(Long.class));

		// Verify JSON structure and values
		assertNotNull("Response Content is null", response.getContentAsString());
		assertTrue(response.getContentAsString().length() == 0);
	}
	
}
