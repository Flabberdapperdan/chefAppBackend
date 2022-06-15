package com.chefApp.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private long id;
	private int userId;

	@Column(nullable = false, length = 100)
	private String name;

	private BigDecimal cost;
	@Column(name = "sale_price")
    private BigDecimal saleprice;

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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(BigDecimal saleprice) {
		this.saleprice = saleprice;
	}

	public List<RecipeIngredient> getRecipeIngredient() {
		return recipeIngredient;
	}

	public void setRecipeIngredient(List<RecipeIngredient> recipeIngredient) {
		this.recipeIngredient = recipeIngredient;
	}
}
