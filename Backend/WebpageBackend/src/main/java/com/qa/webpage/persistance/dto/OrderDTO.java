package com.qa.webpage.persistance.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.qa.webpage.persistance.Customer;
import com.qa.webpage.persistance.LineItem;
import com.qa.webpage.persistance.OrderTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

		private Long Id;
		
		 @OneToMany
		 List<LineItem> lineItems;
		 
		 @ManyToOne
		 Customer customer;
}
