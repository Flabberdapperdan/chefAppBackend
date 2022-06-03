package com.chefApp.demo.dto;

public class RecipeIngredientDTO {

	long recipeId;
	long ingredientId;
	double amount;
	String metric;
	public long getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}
	public long getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	
}
