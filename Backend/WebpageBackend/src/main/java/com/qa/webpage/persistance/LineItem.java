package com.qa.webpage.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    int quantity;

    @ManyToOne
    OrderTable order;

    @ManyToOne
    Item item;
	
}
