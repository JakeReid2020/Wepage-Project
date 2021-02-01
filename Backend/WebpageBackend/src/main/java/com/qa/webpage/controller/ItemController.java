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
import com.qa.webpage.persistance.Item;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.persistance.dto.ItemDTO;
import com.qa.webpage.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/item")
public class ItemController {

	private ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;
	}

	// (CREATE)
	@CrossOrigin	
	@PostMapping("/create")
	public ResponseEntity<ItemDTO> create(@RequestBody Item item) {
		return new ResponseEntity<ItemDTO>(this.service.create(item), HttpStatus.CREATED);
	}

	// (READ)
	@CrossOrigin	
	@GetMapping("/readAll")
	public ResponseEntity<List<ItemDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	// (READ BY ID)
	@CrossOrigin	
	@GetMapping("/read/{id}")
	public ResponseEntity<ItemDTO> readCat(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readone(id));
	}
	
	// (UPDATE BY ID)
	@CrossOrigin	
	@PutMapping("update/{id}")
	public ResponseEntity<ItemDTO> update(@PathVariable("id") Long id, @RequestBody Item item) {
		return new ResponseEntity<>(this.service.update(id, item), HttpStatus.ACCEPTED);
	}

	// (DELETE)
	@CrossOrigin	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("id") Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
