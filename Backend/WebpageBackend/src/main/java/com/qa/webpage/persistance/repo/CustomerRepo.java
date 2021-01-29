package com.qa.webpage.persistance.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.webpage.persistance.Customer;

/*this is where we get a lot of are Crud functionality as it extends 
the JPARepository, we are passing in are objects into this interface */

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
/*
	@Query("SELECT c FROM CUSTOMER c WHERE USERNAME (C.username)")
	public List<Customer> find(@Param("username") String username);
*/
}
