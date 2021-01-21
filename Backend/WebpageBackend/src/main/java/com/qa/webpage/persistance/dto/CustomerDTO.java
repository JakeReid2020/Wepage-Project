package com.qa.webpage.persistance.dto;

public class CustomerDTO {

	// Adding customer attributes:

	private Long Id;

	private String username;

	private String password;

	private String email;
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(Long id, String username, String password, String email) {
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
