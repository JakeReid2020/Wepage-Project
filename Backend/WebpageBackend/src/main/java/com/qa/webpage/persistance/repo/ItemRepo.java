package com.qa.webpage.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.webpage.persistance.Item;

public interface ItemRepo extends JpaRepository<Item, Long>{

}
