package com.chefApp.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	long id;
	int userId;

	@Column(nullable = false, length = 100)
	String name;

	double cost;
	double salePrice;

	@OneToMany(mappedBy = "recipe")
	List<RecipeIngredient> recipeIngredient;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public List<RecipeIngredient> getRecipeIngredient() {
		return recipeIngredient;
	}

	public void setRecipeIngredient(List<RecipeIngredient> recipeIngredient) {
		this.recipeIngredient = recipeIngredient;
	}
}
