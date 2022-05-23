package com.chefApp.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Nutrition {
	   // primary key    AutoIncrement
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
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
