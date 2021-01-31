package com.qa.webpage.persistance.dto;

import com.qa.webpage.persistance.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Customer customer;
}
