package com.qa.webpage.controller;

import java.util.List;
import java.util.ArrayList;


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
import com.qa.webpage.persistance.OrderTable;
import com.qa.webpage.persistance.dto.OrderDTO;



@SpringBootTest(classes = WebpageBackendApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class OrderControllerIntergrationTest {

	// (WE NEED TO SET UP OUR ENVIORMENT/WHAT RESOURCES WE NEED)

	@Autowired // (INJECTION)
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private OrderDTO mapToDto(OrderTable orderTable) {
		return this.mapper.map(orderTable, OrderDTO.class);
	}

	// (SETTING ID WHICH WILL BE USED FOR METHODS)

	private final int TEST_ID = 1;

	// ====================================
	// (CRUD TEST)
	// ====================================

	// (CREATE)
	@Test
	public void createOrder() throws Exception {

		// Staged Resource

		OrderTable TEST_ORDER = new OrderTable();
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/order/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_ORDER))
				.accept(MediaType.APPLICATION_JSON);

		// (EXPECTION OF RESULT)
		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(mapToDto(TEST_ORDER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (READALL)
	@Test
	public void readOrder() throws Exception {
		List<OrderDTO> TEST_LISTORDER = new ArrayList<>();
		TEST_LISTORDER.add(new OrderDTO());
		TEST_LISTORDER.add(new OrderDTO());
		TEST_LISTORDER.add(new OrderDTO());
		
		
		// (Prepare Rest Request)
		MockHttpServletRequestBuilder mockRequest = 
		MockMvcRequestBuilders.request(HttpMethod.GET, "/order/readAll");
	
		
		// (EXPECTION OF RESULT)

		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(TEST_LISTORDER));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (READ BY ID)
	@Test
	public void readByIdOrder() throws Exception {

		// (WHAT WE EXPECT OUT OF THE RESULT)

		OrderDTO TEST_CUSTOMERDTO = new OrderDTO();

		// (RUNNING TEST)

		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/order/read/" + TEST_ID)
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
	public void updateByIdOrder() throws Exception {
		// Staged Resource
		OrderTable TEST_ORDER = new OrderTable();
		TEST_ORDER.setId((long)TEST_ID);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/order/update/"+TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_ORDER))
				.accept(MediaType.APPLICATION_JSON);

		// (EXPECTION OF RESULT)
		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(mapToDto(TEST_ORDER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (DELETE)
	@Test
	public void deleteOrder() throws Exception {

		// (RUNNING TEST)
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/order/delete/" + TEST_ID);

		// (EXPECTION OF RESULT)
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}

}
