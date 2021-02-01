package com.qa.webpage.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.qa.webpage.WebpageBackendApplication;
import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.service.CustomerService;

@SpringBootTest(classes = WebpageBackendApplication.class)
public class CustomerControllerUnitTest {

	@Autowired
	private CustomerController controller;
	
	@MockBean
	private CustomerService service;
	
	private List<Customer> customerList;
	private CustomerDTO customerDTO;
	private Customer testCustomer;
	private Long Id;
	private ModelMapper mapper = new ModelMapper();
	
	private CustomerDTO mapToDTO(Customer customer) {
		return this.mapper.map(customer, CustomerDTO.class);
	}
	 
	
	
	@BeforeEach
	void init() {
		this.Id = 1L;
		this.customerList = new ArrayList<>();
		this.testCustomer = new Customer(Id, "JAFFA", "DOGS29", "JAKE@GMAIL.COM",null);
		this.customerDTO = new CustomerDTO(Id, "JAFFA", "DOGS29", "JAKE@GMAIL.COM");
		
		this.customerList.add(testCustomer);
		this.customerDTO = this.mapToDTO(testCustomer);
	}
	
	
	//============= (CREATE)
	
	
	@Test
	public void createTest() {
		// (RULES)
		Mockito.when(this.service.create(testCustomer)).thenReturn(customerDTO);
		
		// (ASSERTIONS & VERIFY)
		Assertions.assertThat(new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.CREATED))
				.usingRecursiveComparison().isEqualTo(controller.create(testCustomer));
		Mockito.verify(this.service, Mockito.times(1)).create(testCustomer);
	}
	
	
	//============= (READ)
	
	@Test
	public void readAllTest() {
		// (RULES)
		Mockito.when(this.service.readAll()).thenReturn(customerList.stream().map
				(this::mapToDTO).collect(Collectors.toList()));
		
		// (ASSERTIONS & VERIFY)
		assertThat(ResponseEntity.ok(this.service.readAll()))
				.usingRecursiveComparison().isEqualTo(controller.readAll());
		
		Mockito.verify(this.service, Mockito.times(2)).readAll();
	}
	
	
	//============== (READ BY ID)
	
	@Test
	public void readOneTest() {
		Mockito.when(this.service.readone(Id)).thenReturn(customerDTO);
		
		assertThat(ResponseEntity.ok(this.service.readone(Id)))
				.usingRecursiveComparison().isEqualTo(controller.readCustomer(Id));
		
		Mockito.verify(this.service, Mockito.times(2)).readone(Id);
	}
	
	//============= (UPDATE)
	
	@Test
	public void updateTest() {
		// (INITIALISING)
		Customer updatedCustomer = new Customer(Id, "JAFFA", "DOGS29", "JAKE@GMAIL.COM",null);
		CustomerDTO updatedCustomerDto = new CustomerDTO(Id, "JAFFA", "DOGS29", "JAKE@GMAIL.COM");
		
		// (RULES)
		Mockito.when(this.service.update(Id, updatedCustomer)).thenReturn(updatedCustomerDto);
		
		// (ASSERTIONS & VERIFY)
		assertThat(new ResponseEntity<>(updatedCustomerDto, HttpStatus.ACCEPTED))
				.usingRecursiveComparison().isEqualTo(controller.update(Id, updatedCustomer));
		
		Mockito.verify(this.service, Mockito.times(1)).update(Id, updatedCustomer);		
	}
	
	//============= (DELETE)
	
	@Test
	public void deleteTest() {
		// (RULES)
		Mockito.when(this.service.delete(Id)).thenReturn(true);
		
		// (ASSERTIONS & VERIFY)
		assertThat(new ResponseEntity<>(HttpStatus.NO_CONTENT))
				.usingRecursiveComparison().isEqualTo(controller.deleteCustomer(Id));
		
		Mockito.verify(this.service, Mockito.times(1)).delete(Id);
	}
	
}
