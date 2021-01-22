package controller;

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

@SpringBootTest (classes = WebpageBackendApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:data-test.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class CustomerControllerTest {

	//(WE NEED TO SET UP OUR ENVIORMENT/WHAT RESOURCES WE NEED)
	
	@Autowired // (INJECTION)
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;
	
	private CustomerDTO mapToDto(Customer customer) {
		return this.mapper.map(customer, CustomerDTO.class);
	}
	
	//(SETTING ID WHICH WILL BE USED FOR METHODS)
	
	private final int TEST_ID = 1;
		
	// ====================================
	// (CRUD TEST)
	// ====================================
	
	// (CREATE)
	@Test
	public void createCustomer() {
		
	}
	// (READALL)
	@Test
	public void readCustomer() {
			
	}
	
	// (READ BY ID)
	@Test
	public void readByIdcustomer() throws Exception {
		
		// (WHAT WE EXPECT OUT OF THE RESULT)
		
		CustomerDTO TEST_CUSTOMER = new CustomerDTO(1L,"JAFFA", "DOGS29", "JAKE@GMAIL.COM"); 
		
		// (RUNNING TEST)
		
		MockHttpServletRequestBuilder mockRequest = 
		MockMvcRequestBuilders.request(HttpMethod.GET, "/customer/read/"+TEST_ID);
		
		// (EXPECTION OF RESULT)
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_CUSTOMER));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// (RUN & ASSERT)
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// (UPDATE)
	@Test
	public void updateCustomer() {
		
	}
	// (UPDATE BY ID)
	@Test
	public void updateByIdCustomer() {
		
	}
	// (DELETE)
	@Test
	public void deleteCustomer() {
		
	}
	
}
