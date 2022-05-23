package com.chefApp.demo.model;

public class IngredientFactory {
	
	public static Ingredient getIngredient(String type) {
		if("sla".equals(type)) {
			Sla sla = new Sla();
			sla.setCost(2);
			return sla;
		} else {
			if("kippenpoot".equals(type)) {
				return new Kippenpoot();
			}
		}
		
		return null; // foei
	}

}
