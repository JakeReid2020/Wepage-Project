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

import com.qa.webpage.persistance.OrderTable;
import com.qa.webpage.persistance.dto.OrderDTO;
import com.qa.webpage.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class OrderController {

	private OrderService service;

	public OrderController(OrderService service) {
		super();
		this.service = service;
	}

	// (CREATE)
	@PostMapping("/create")
	public ResponseEntity<OrderDTO> create(@RequestBody OrderTable ordertable) {
		return new ResponseEntity<OrderDTO>(this.service.create(ordertable), HttpStatus.CREATED);
	}

	// (READ)
	@GetMapping("/readAll")
	public ResponseEntity<List<OrderDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	// (READ BY ID)
	@GetMapping("/read/{id}")
	public ResponseEntity<OrderDTO> readCat(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readone(id));
	}
	
	// (UPDATE BY ID)
	@PutMapping("update/{id}")
	public ResponseEntity<OrderDTO> update(@PathVariable("id") Long id, @RequestBody OrderTable ordertable) {
		return new ResponseEntity<>(this.service.update(id, ordertable), HttpStatus.ACCEPTED);
	}
	
	// (DELETE)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<OrderDTO> deleteOrder(@PathVariable("id") Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
