package com.chefApp.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Allergen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Soms gaat AUTO fout. Bijvoorbeeld bij DB2. Maar dat zal zo'n vaart nog niet lopen. 
	private long id;

	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
