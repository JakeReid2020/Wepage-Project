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
import com.qa.webpage.persistance.Item;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.persistance.dto.ItemDTO;

@SpringBootTest(classes = WebpageBackendApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class ItemControllerTest {

	@Autowired // (INJECTION)
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private ItemDTO mapToDto(Item item) {
		return this.mapper.map(item, ItemDTO.class);
	}
	
	private final int TEST_ID = 1;

	// ====================================
	// (CRUD TEST)
	// ====================================

	// (CREATE)
	@Test
	public void createItem () throws Exception {

		// Staged Resource

		Item TEST_ITEM = new Item(4L,null, "Pack of 40 cards","GTA",20.0);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/item/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_ITEM))
				.accept(MediaType.APPLICATION_JSON);

		// (EXPECTION OF RESULT)
		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(mapToDto(TEST_ITEM)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (READALL)
	@Test
	public void readItem() throws Exception {
		List<ItemDTO> TEST_LISTITEM = new ArrayList<>();
		TEST_LISTITEM.add(new ItemDTO(1L, "Escape", "Pack of 40 cards", 20.0));
		TEST_LISTITEM.add(new ItemDTO(2L, "Maths", "Pack of 40 cards", 20.0));
		TEST_LISTITEM.add(new ItemDTO(3L, "Super Sign", "Pack of 40 cards", 20.0));
		
		// (Prepare Rest Request)
		MockHttpServletRequestBuilder mockRequest = 
		MockMvcRequestBuilders.request(HttpMethod.GET, "/item/readAll");
	
		// (EXPECTION OF RESULT)
		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(TEST_LISTITEM));
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

		ItemDTO TEST_ITEMDTO = new ItemDTO(1L, "Escape", "Pack of 40 cards", 20.0);

		// (RUNNING TEST)

		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/item/read/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				//.content(this.jsonifier.writeValueAsString(TEST_CUSTOMER))
				.accept(MediaType.APPLICATION_JSON);

		
		// (EXPECTION OF RESULT)

		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(TEST_ITEMDTO));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (UPDATE BY ID)
	@Test
	public void updateByIdItem() throws Exception {
		// Staged Resource
		Item TEST_ITEM = new Item(1L,null, "GTA", "Pack of 100 cards", 20.0);
		TEST_ITEM.setId((long)TEST_ID);
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/item/update/"+TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_ITEM))
				.accept(MediaType.APPLICATION_JSON);

		// (EXPECTION OF RESULT)
		ResultMatcher matchContent = MockMvcResultMatchers.content()
		.json(this.jsonifier.writeValueAsString(mapToDto(TEST_ITEM)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}

	// (DELETE)
	@Test
	public void deleteItem() throws Exception {

		// (RUNNING TEST)
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/item/delete/" + TEST_ID);

		// (EXPECTION OF RESULT)
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
}
