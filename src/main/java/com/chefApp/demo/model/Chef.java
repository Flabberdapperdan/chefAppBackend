package com.chefApp.demo.model;

public class Chef {
	
	private static Chef chef;
	
	public synchronized static Chef getInstance() {
		if(chef == null) {
			chef = new Chef();
		}
		
		return chef;
	}
	
	
	// using the Singleton pattern
	private Chef() {
		
	}
}
