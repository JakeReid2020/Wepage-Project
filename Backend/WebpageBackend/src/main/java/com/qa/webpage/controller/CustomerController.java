package com.qa.webpage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("/register")
	public ResponseEntity<CustomerDTO> create(@RequestBody Customer customer) {
		return new ResponseEntity<CustomerDTO>(this.service.create(customer), HttpStatus.CREATED);
	}

	// (READ)
	@GetMapping("/readAll")
	public ResponseEntity<List<CustomerDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
}
