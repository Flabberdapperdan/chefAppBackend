package com.chefApp.demo.DTO;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;

public class RecipeIngredientDTO {
    Recipe recipe;
    Ingredient ingredient;
    double amount;
    String metric;

    // getters and setters \\
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
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
