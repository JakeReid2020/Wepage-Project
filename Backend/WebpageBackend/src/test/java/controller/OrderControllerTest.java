package controller;

import javax.persistence.criteria.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.webpage.WebpageBackendApplication;
import com.qa.webpage.persistance.Item;
import com.qa.webpage.persistance.dto.ItemDTO;
import com.qa.webpage.persistance.dto.OrderDTO;

@SpringBootTest(classes = WebpageBackendApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:data-test.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class OrderControllerTest {

	@Autowired // (INJECTION)
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private OrderDTO mapToDto(Order order) {
		return this.mapper.map(order, OrderDTO.class);

	}
}