package com.qa.webpage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.persistance.repo.CustomerRepo;

@Service
public class CustomerService {

	private CustomerRepo repo;
	private ModelMapper mapper;

	// uses the ModelMapper bean to the CustomerService as a dependency:
	@Autowired
	public CustomerService(CustomerRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// This method uses the MethodMapper to convert a Customer to a CustomerDTO:
	private CustomerDTO mapToDTO(Customer customer) {
		return this.mapper.map(customer, CustomerDTO.class);
	}
	// CRUD

	// (Create)
	public CustomerDTO create(Customer customer) {
		return this.mapToDTO(this.repo.save(customer));
	}

	// (ReadAll)
	public List<CustomerDTO> readAll() {

		List<Customer> catList = this.repo.findAll();
		List<CustomerDTO> catListDTO = catList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return catListDTO;
	}
}