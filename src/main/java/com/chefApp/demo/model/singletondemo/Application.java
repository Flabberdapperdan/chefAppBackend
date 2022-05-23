package com.chefApp.demo.model.singletondemo;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.IngredientFactory;

public class Application {
	
	public static void main(String[] args) {
		Chef c = Chef.getInstance();
		Chef d = Chef.getInstance();
		Chef e = Chef.getInstance();
		
//		Chef d = new Chef();// vout want dit willen we dus voorkomen met Singleton pattern
		
		
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
	}
}
