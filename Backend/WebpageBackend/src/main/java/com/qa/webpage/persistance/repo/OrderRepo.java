package com.qa.webpage.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.webpage.persistance.OrderTable;


@Repository
public interface OrderRepo extends JpaRepository<OrderTable, Long> {

}
