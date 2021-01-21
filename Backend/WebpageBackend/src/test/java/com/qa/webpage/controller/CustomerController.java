package com.qa.webpage.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.webpage.WebpageBackendApplication;
import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.dto.CustomerDTO;

@SpringBootTest(classes = WebpageBackendApplication.class)
public class CustomerController {
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "reg")
public class CustomerControllerIntergrationTest{
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private CustomerDTO mapToDto(Customer customer) {
		return this.mapper.map(customer, CustomerDTO.class);
	}
	
	private final int TEST_ID = 1;
	
	@Test
	public void createCustomer() {
		
		Customer TEST_CUSTOMER = new Customer(0L,"Jake","101","Jake@hotmail.com")
		
		MockHttpServletRequestBuilder mockRequest = 
		MockMvcRequestBuilders.request(HttpMethod.POST, "/customer/read"+TEST_ID)
		.contentType(MediaType.APPLICATION_JSON)
		//.content(this.jsonifier.writeValueAsString(value)) this will be needed for the body in the create method.
		.accept(MediaType.APPLICATION_JSON);
		
		TEST_CUSTOMER.setId(4L);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDto(TEST_CUSTOMER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
	}
	
	@Test
	public void readCustomer() {
		
		CustomerDTO TEST_CUSTOMER = new CustomerDTO(1L,"Jake","Jaffers","Jakereid@hotmail.co.uk");
		
		MockHttpServletRequestBuilder mockRequest = 
		MockMvcRequestBuilders.request(HttpMethod.GET, "/customer/read"+TEST_ID)
		.contentType(MediaType.APPLICATION_JSON)
		//.content(this.jsonifier.writeValueAsString(value)) this will be needed for the body in the create method.
		.accept(MediaType.APPLICATION_JSON);
		
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_CUSTOMER));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
}

}
