package com.chefApp.demo.model;

public class Application {
	
	public static void main(String[] args) {
		Chef c = Chef.getInstance();
		Chef d = Chef.getInstance();
		Chef e = Chef.getInstance();
		
//		Chef d = new Chef();// vout 
		
		
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		
		Ingredient i = IngredientFactory.getIngredient("sla");
		
	}
}
