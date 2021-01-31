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
import com.qa.webpage.persistance.Item;
import com.qa.webpage.persistance.dto.CustomerDTO;
import com.qa.webpage.persistance.dto.ItemDTO;
import com.qa.webpage.persistance.repo.ItemRepo;

@SpringBootTest(classes = WebpageBackendApplication.class)
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class ItemServiceUnitTest {

	@Autowired
	private ItemService service;
	

	
	@MockBean
	private ItemRepo repoMock;
	
	private List<Item> itemlist; 
	private ItemDTO itemdto;
	private Item itemtest; 
	private Long Id;
	

	
	@BeforeEach
	void init() {	
	this.itemlist = new ArrayList<>();
	this.itemdto = new ItemDTO (1L,"Escape","Pack of 40 cards",20.00);
	this.itemtest = new Item (1L,null,"Escape","Pack of 40 cards",20.00);
	this.Id = 1L;
	}
	//============= (CREATE)
		@Test
		public void  createTest() {
			// (RULES)
			Mockito.when(this.repoMock.save(Mockito.any(Item.class))).thenReturn(this.itemtest);
			
			// (ACTION)
			ItemDTO result = this.service.create(this.itemtest);
			
			// (ASSERTIONS & VERIFY)
			Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.itemdto);
			Mockito.verify(this.repoMock,  Mockito.times(1)).save(this.itemtest);
		}
		
	//============== (READ)
		@Test
		public void  readAllTest(){
			// (RULES)
			Mockito.when(this.repoMock.findAll()).thenReturn(this.itemlist);
			// (ACTION)
			List<ItemDTO> result = this.service.readAll(); 
			// (ASSERTIONS & VERIFY)
			Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.itemlist);
			Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
		}
		
	//============== (READ BY ID)
		@Test
		public void  readoneTest() {
			// (RULES)
			Mockito.when(this.repoMock.findById(this.Id)).thenReturn(Optional.of(this.itemtest));
			// (ACTION)
			ItemDTO result = this.service.readone(this.Id); 
			// (ASSERTIONS & VERIFY)
			Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(this.itemdto);
			Mockito.verify(this.repoMock, Mockito.times(1)).findById(this.Id);
		}
		
		//============== (UPDATE BY ID)
		@Test
		public void updateTest() {
			
			// (INITIALISING)
			Item itemUpdate = new Item(1L,null,"Escape","Pack of 40 cards",20.00);
			itemUpdate.setId(1L);
			
			ItemDTO updatedItemDto = new ItemDTO(1L,"Escape","Pack of 40 cards",20.00);
			this.itemtest.setId(1L);
			// (RULES)
			Mockito.when(this.repoMock.findById(1L)).thenReturn(Optional.of(this.itemtest));
			Mockito.when((this.repoMock.save(Mockito.any(Item.class)))).thenReturn(itemUpdate);
			// (ACTION)
			ItemDTO result = this.service.update(1L,itemUpdate);
			// (ASSERTIONS & VERIFY)
			Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(updatedItemDto);
			Mockito.verify(this.repoMock, Mockito.times(1)).findById(1L);
		}
		
	// (DELETE BY ID)
		@Test
		public void deleteTestPass() {
			// (RULES)
			Mockito.when(this.repoMock.existsById(Id)).thenReturn(false);
			// (ASSERTIONS & VERIFY)
			Assertions.assertThat(this.service.delete(Id)).isTrue();
			Mockito.verify(this.repoMock, Mockito.times(1)).existsById(this.Id);
		}
	
}
