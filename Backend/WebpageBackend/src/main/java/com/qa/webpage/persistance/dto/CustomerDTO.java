package com.qa.webpage.persistance.dto;

import com.qa.webpage.persistance.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	// Adding customer attributes:

	private Long Id;

	private String username;

	private String password;

	private String email;

}
