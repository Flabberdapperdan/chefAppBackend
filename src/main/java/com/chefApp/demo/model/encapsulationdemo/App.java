package com.chefApp.demo.model.encapsulationdemo;

import com.chefApp.demo.model.Ingredient;

public class App {

	public static void main(String[] args) {

		Ingredient i = new Ingredient();
//		i.cost = -3;
		i.setCost(-3);
		
		System.out.println(i.getCost());

	}

}
