package com.qa.webpage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.service.CustomerService;

@RestController
@RequestMapping("/customer")

public class CustomerController {

	private CustomerService service;

	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}

	// (CREATE)
//	@CrossOrigin(origins = "http://localhost:8080")
//	@CrossOrigin(origins = "*")	
	@CrossOrigin	
	@PostMapping("/register")
	public ResponseEntity<CustomerDTO> create(@RequestBody Customer customer) {
		return new ResponseEntity<CustomerDTO>(this.service.create(customer), HttpStatus.CREATED);
	}

	// (READ)
	@GetMapping("/readAll")
	public ResponseEntity<List<CustomerDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	// (READ BY ID)
	@GetMapping("/read/{id}")
	public ResponseEntity<CustomerDTO> readCustomer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readone(id));
	}
	
	// (UPDATE BY ID)
	@PutMapping("update/{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable("id") Long id, @RequestBody Customer customer) {
		return new ResponseEntity<>(this.service.update(id, customer), HttpStatus.ACCEPTED);
	}
	
	// (DELETE)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("id") Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// (LOGIN)
	@GetMapping("/login")
	public ResponseEntity<Long> login(@RequestBody Customer customer) 
	{
		Long customer_id          = this.service.login(customer);
		ResponseEntity<Long> resp = new ResponseEntity<Long>(customer_id, HttpStatus.ACCEPTED);	
		
		return resp;
	}
	
}
