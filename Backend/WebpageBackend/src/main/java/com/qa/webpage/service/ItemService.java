package com.qa.webpage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.Item;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.persistance.dto.ItemDTO;

import com.qa.webpage.persistance.repo.ItemRepo;


@Service
public class ItemService {
	
	private ItemRepo repo;
	private ModelMapper mapper;

	// uses the ModelMapper bean to the CustomerService as a dependency:
	@Autowired
	public ItemService(ItemRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// This method uses the MethodMapper to convert a Customer to a CustomerDTO:
	private ItemDTO mapToDTO(Item item) {
		return this.mapper.map(item, ItemDTO.class);
	}

	// (CREATE)
	public ItemDTO create(Item item) {
		return this.mapToDTO(this.repo.save(item));
	}
	
	
	// (READ)
	public List<ItemDTO> readAll() {

		List<Item> catList = this.repo.findAll();
		List<ItemDTO> catListDTO = catList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return catListDTO;
	}
	
	// (READ BY ID)
	public ItemDTO readone(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	// (DELETE BY ID)
		public boolean delete(Long id) {
			this.repo.deleteById(id);

			return !this.repo.existsById(id);
		}
	
	
}
