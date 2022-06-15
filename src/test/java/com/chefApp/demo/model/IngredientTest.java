package com.chefApp.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IngredientTest {
	
	
	private Ingredient ingredient;
	
	@BeforeEach
	public void setup() {
		this.ingredient = new Ingredient();
	}
	
	@Test
	public void testSetAndGetName() {
		this.ingredient.setName("Aardappelen");
		assertEquals("Aardappelen", ingredient.getName());
	}
	
	@Test
	public void testSetAndGetPrice() {
		this.ingredient.setMarketprice(BigDecimal.valueOf(15.0));
		assertEquals(BigDecimal.valueOf(15.0), ingredient.getMarketprice());
	}

}
