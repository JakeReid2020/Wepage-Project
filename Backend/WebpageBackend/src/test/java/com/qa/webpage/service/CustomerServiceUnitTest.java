package com.qa.webpage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.webpage.WebpageBackendApplication;
import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.persistance.repo.CustomerRepo;

@SpringBootTest(classes = WebpageBackendApplication.class)
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class CustomerServiceUnitTest {

	@Autowired
	private CustomerService service; 
	
	// (MOCK)
	@MockBean
	private CustomerRepo repoMock;
	
	//(INITIALIZING)
	private List<Customer> customerlist; 
	private CustomerDTO customerdto;
	private Customer customertest; 
	private Long Id;
	
	
	//(should be executed before each Test)
	@BeforeEach
	void init() {	
	this.customerlist = new ArrayList<>();
	this.customerdto = new CustomerDTO (1l,"JAFFA", "DOGS29", "JAKE@GMAIL.COM");
	this.customertest = new Customer (1l,"JAFFA", "DOGS29", "JAKE@GMAIL.COM", null);
	this.Id = 1L;
	}
	// CRUD
	
	//============= (CREATE)
	@Test
	public void  CreateTest() {
		// (RULES)
		Mockito.when(this.repoMock.save(Mockito.any(Customer.class))).thenReturn(this.customertest);
		
		// (ACTION)
		CustomerDTO result = this.service.create(this.customertest);
		
		// (ASSERTIONS & VERIFY)
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.customerdto);
		Mockito.verify(this.repoMock,  Mockito.times(1)).save(this.customertest);
	}

	//============== (READ)
	@Test
	public void  ReadAllTest(){
		// (RULES)
		Mockito.when(this.repoMock.findAll()).thenReturn(this.customerlist);
		// (ACTION)
		List<CustomerDTO> result = this.service.readAll(); 
		// (ASSERTIONS & VERIFY)
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.customerlist);
		Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
	}
	
	//============== (READ BY ID)
	@Test
	public void  ReadOneTest() {
		// (RULES)
		Mockito.when(this.repoMock.findById(this.Id)).thenReturn(Optional.of(this.customertest));
		// (ACTION)
		CustomerDTO result = this.service.readone(this.Id); 
		// (ASSERTIONS & VERIFY)
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.customerdto);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(this.Id);
	}
	
	//============== (UPDATE BY ID)
	@Test
	public void UpdateTest() {
		
		// (INITIALISING)
		Customer customerUpdate = new Customer(1l,"JAMEEEE", "T1T1", "JAMEE@GMAIL.COM", null);
		customerUpdate.setId(1L);
		
		CustomerDTO updatedCustomerDto = new CustomerDTO(1l,"JAMEEEE", "T1T1", "JAMEE@GMAIL.COM");
		this.customertest.setId(1L);
		// (RULES)
		Mockito.when(this.repoMock.findById(1L)).thenReturn(Optional.of(this.customertest));
		Mockito.when((this.repoMock.save(Mockito.any(Customer.class)))).thenReturn(customerUpdate);
		// (ACTION)
		CustomerDTO result = this.service.update(1L,customerUpdate);
		// (ASSERTIONS & VERIFY)
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(updatedCustomerDto);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(1L);
	}
	// (DELETE BY ID)
	@Test
	public void DeleteTestPass() {
		// (RULES)
		Mockito.when(this.repoMock.existsById(Id)).thenReturn(false);
		// (ASSERTIONS & VERIFY)
		Assertions.assertThat(this.service.delete(Id)).isTrue();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(this.Id);
	}
	// (DELETE BY ID *WRONG ID*)
	@Test
	public void DeleteTestfail() {
		// (RULES)
		Mockito.when(this.repoMock.existsById(Id)).thenReturn(true);
		// (ASSERTIONS & VERIFY)
		Assertions.assertThat(this.service.delete(Id)).isFalse();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(this.Id);
	}

}
