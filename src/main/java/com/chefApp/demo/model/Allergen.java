package com.chefApp.demo.model;

import javax.persistence.Entity;

@Entity
public class Allergen {
	long id;
	String Name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	
	

}
