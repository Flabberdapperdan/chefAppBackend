package com.chefApp.demo.model.factorydemo;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.IngredientFactory;
import com.chefApp.demo.model.singletondemo.Chef;

public class Application {
	
	public static void main(String[] args) {
		Ingredient ingredient = IngredientFactory.getIngredient("sla");
	}
}
