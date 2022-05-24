package com.chefApp.demo.model;

import javax.persistence.*;

@Entity
public class IngredientNutrition {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    @ManyToOne
    @MapsId("nutritionId")
    @JoinColumn(name = "nutrition_id")
    Nutrition nutrition;

    Integer amount;
    String metric;

    // getters and setters \\
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
