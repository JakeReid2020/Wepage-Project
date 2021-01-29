package controller;

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
@Sql(scripts = { "classpath:data-test.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	
}
