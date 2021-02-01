package com.qa.webpage.controller;

import java.util.ArrayList;
import java.util.List;

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
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class CustomerControllerIntergrationTest {

	// (WE NEED TO SET UP OUR ENVIORMENT/WHAT RESOURCES WE NEED)

	@Autowired // (INJECTION)
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private CustomerDTO mapToDto(Customer customer) {
		return this.mapper.map(customer, CustomerDTO.class);
	}

	// (SETTING ID WHICH WILL BE USED FOR METHODS)

	private final int TEST_ID = 1;

	// ====================================
	// (CRUD TEST)
	// ====================================

	// (CREATE)
	@Test
	public void createCustomer() throws Exception {

		// Staged Resource

		Customer TEST_CUSTOMER = new Customer(4L,"JAFFA", "DOGS29", "JAKE@GMAIL.COM", null);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/customer/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_CUSTOMER))
				.accept(MediaType.APPLICATION_JSON);

		// (EXPECTION OF RESULT)
		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(mapToDto(TEST_CUSTOMER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (READALL)
	@Test
	public void readCustomer() throws Exception {
		List<CustomerDTO> TEST_LISTCUSTOMER = new ArrayList<>();
		TEST_LISTCUSTOMER.add(new CustomerDTO(1L, "JAFFA", "DOGS29", "JAKE@GMAIL.COM"));
		TEST_LISTCUSTOMER.add(new CustomerDTO(2L, "GEORGEEOO", "20GEORGE20", "GEORGE@GMAIL.COM"));
		TEST_LISTCUSTOMER.add(new CustomerDTO(3L, "MAT", "MAT2020", "MAT@HOTMAIL.CO.UK"));
		
		
		// (Prepare Rest Request)
		MockHttpServletRequestBuilder mockRequest = 
		MockMvcRequestBuilders.request(HttpMethod.GET, "/customer/readAll");
	
		
		// (EXPECTION OF RESULT)

		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(TEST_LISTCUSTOMER));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (READ BY ID)
	@Test
	public void readByIdcustomer() throws Exception {

		// (WHAT WE EXPECT OUT OF THE RESULT)

		CustomerDTO TEST_CUSTOMERDTO = new CustomerDTO(1L, "JAFFA", "DOGS29", "JAKE@GMAIL.COM");

		// (RUNNING TEST)

		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/customer/read/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				//.content(this.jsonifier.writeValueAsString(TEST_CUSTOMER))
				.accept(MediaType.APPLICATION_JSON);

		
		// (EXPECTION OF RESULT)

		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(TEST_CUSTOMERDTO));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (UPDATE BY ID)
	@Test
	public void updateByIdCustomer() throws Exception {
		// Staged Resource
		Customer TEST_CUSTOMER = new Customer(1L,"JAMMEY", "DOGS29", "JAKE@GMAIL.COM", null);
		TEST_CUSTOMER.setId((long)TEST_ID);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/customer/update/"+TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_CUSTOMER))
				.accept(MediaType.APPLICATION_JSON);

		// (EXPECTION OF RESULT)
		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(mapToDto(TEST_CUSTOMER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (DELETE)
	@Test
	public void deleteCustomer() throws Exception {

		// (RUNNING TEST)
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/customer/delete/" + TEST_ID);

		// (EXPECTION OF RESULT)
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}

}
