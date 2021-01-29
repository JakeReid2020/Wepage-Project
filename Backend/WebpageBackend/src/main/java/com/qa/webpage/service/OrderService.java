package com.qa.webpage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.webpage.persistance.OrderTable;
import com.qa.webpage.persistance.dto.OrderDTO;
import com.qa.webpage.persistance.repo.OrderRepo;
import com.qa.webpage.utils.MyBeanUtils;

@Service
public class OrderService {

	private OrderRepo repo;
	private ModelMapper mapper;

	// uses the ModelMapper bean to the CustomerService as a dependency:
	@Autowired
	public OrderService(OrderRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// This method uses the MethodMapper to convert a Customer to a CustomerDTO:
	private OrderDTO mapToDTO(OrderTable ordertable) {
		return this.mapper.map(ordertable, OrderDTO.class);
	}
	// CRUD

	// (CREATE)
	public OrderDTO create(OrderTable ordertable) {
		return this.mapToDTO(this.repo.save(ordertable));
	}

	// (READ)
	public List<OrderDTO> readAll() {

		List<OrderTable> orderList = this.repo.findAll();
		List<OrderDTO> orderListDTO = orderList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return orderListDTO;
	}
	
	// (READ BY ID)
	public OrderDTO readone(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	// (UPDATE BY ID)
	public OrderDTO update(long id, OrderTable ordertable) {

		OrderTable updatedOrder = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(ordertable, updatedOrder);

		return this.mapToDTO(this.repo.save(updatedOrder));
	}
	
	// (DELETE BY ID)
	public boolean delete(Long id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}
	
}
