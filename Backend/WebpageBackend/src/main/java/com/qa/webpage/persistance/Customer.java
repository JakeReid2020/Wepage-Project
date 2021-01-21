package com.qa.webpage.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
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

	public Customer() {
		super();
	}
	
	public Customer(Long id, @NotNull String username, @NotNull String password, @NotNull String email) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [Id=" + Id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}

}
