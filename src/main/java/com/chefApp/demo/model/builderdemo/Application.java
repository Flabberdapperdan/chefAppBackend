package com.chefApp.demo.model.builderdemo;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.IngredientFactory;

public class Application {
	
	public static void main(String[] args) {
	
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(45);
		stringBuilder.append(true);
		stringBuilder.append('R');
		
		stringBuilder.reverse();
		
		String label = stringBuilder.toString();
		
		System.out.println(label);
		
	}
}
