package com.qa.webpage.persistance;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin
public class Customer {

	// Adding customer attributes:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String email;
	
	@OneToMany(mappedBy = "customer", fetch=FetchType.EAGER)
	private List<OrderTable> ordertable;
	
	/*
	@OneToMany(mappedBy = "customer")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrderTable> orderList;
	*/
}
