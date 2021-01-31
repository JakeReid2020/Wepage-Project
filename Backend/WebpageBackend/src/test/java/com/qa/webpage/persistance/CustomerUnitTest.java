package com.qa.webpage.persistance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import org.assertj.core.api.Assertions;

import com.qa.webpage.WebpageBackendApplication;


@SpringBootTest(classes = WebpageBackendApplication.class)
public class CustomerUnitTest {
	
	private Customer testCustomer = new Customer(1l,"Jake","Reid", null, null);
	private Customer testCustomer2 = new Customer(1l,"Jake","Reid", null, null);
	private Customer testCustomer3 = new Customer(2l,"Jake","Reid", null, null);
	private Customer nullCustomer1 = new Customer(null,null,null, null, null);
	private Customer nullCustomer2 = new Customer(1l,null,null, null, null);
	private Customer nullCustomer3 = new Customer(1l,"Bob",null, null, null);
	private Item testItem = new Item();

	
	@Test
	public void testSetters() {
		testCustomer.setId(1l);
		testCustomer.setUsername("Tim");
		testCustomer.setPassword("NoName");
		assertFalse(testCustomer.equals(testCustomer2));
	}
	
	@Test
	public void testGetters() {
		long id = 1l;
		String username = "Jake";
		String password = "Reid";
		assertTrue(testCustomer.getId().equals(id));
		assertTrue(testCustomer.getUsername().equals(username));
		assertTrue(testCustomer.getPassword().equals(password));
	}
	
	@Test
	public void testEquals() {
		assertFalse(nullCustomer1.equals(testCustomer));
		assertFalse(nullCustomer2.equals(testCustomer));
		assertFalse(nullCustomer3.equals(testCustomer));
		assertFalse(testCustomer.equals(nullCustomer1));
		assertFalse(testCustomer.equals(nullCustomer2));
		assertFalse(testCustomer.equals(nullCustomer3));
		assertTrue(testCustomer.equals(testCustomer));
		assertFalse(testCustomer.equals(null));
		assertFalse(testCustomer.equals(testItem));
		assertFalse(testCustomer.equals(testCustomer3));
	}
}
