package com.qa.webpage.service;

import java.beans.Statement;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.persistance.repo.CustomerRepo;
import com.qa.webpage.utils.MyBeanUtils;

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

	// (CREATE)
	public CustomerDTO create(Customer customer) {
		return this.mapToDTO(this.repo.save(customer));
	}

	// (READ)
	public List<CustomerDTO> readAll() {

		List<Customer> catList = this.repo.findAll();
		List<CustomerDTO> catListDTO = catList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return catListDTO;
	}
	
	// (READ BY ID)
	public CustomerDTO readone(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	// (UPDATE BY ID)
	
	public CustomerDTO update(long id, Customer customer) {

		Customer updatedCustomer = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(customer, updatedCustomer);

		return this.mapToDTO(this.repo.save(updatedCustomer));
	}
	
	// (DELETE BY ID)
	public boolean delete(Long id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}
	
	// (LOGIN)
	public Long login(Customer customer) {
		
		/*
		Statement statement = connection.createStatement();
		statement.executeQuery("SELECT * FROM MILESTONE WHERE ID= 'D58BE'");
		*/
		
		return 1L ;
	}
}