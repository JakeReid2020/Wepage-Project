package com.qa.webpage.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

	// Adding Item attributes:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private Double price;

	public Item() {
		super();
	}

	public Item(Long id, @NotNull String name, @NotNull String description, Double price) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescrition(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [Id=" + Id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}
}