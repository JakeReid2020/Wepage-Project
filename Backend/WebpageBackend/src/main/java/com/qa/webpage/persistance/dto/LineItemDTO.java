package com.qa.webpage.persistance.dto;

import javax.persistence.ManyToOne;

import com.qa.webpage.persistance.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemDTO {

	private long id;
	private int quantity;	
	private Item item;
}
