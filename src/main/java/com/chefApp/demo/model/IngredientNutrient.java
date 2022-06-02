package com.chefApp.demo.model;

import javax.persistence.*;

@Entity
public class IngredientNutrient {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    @ManyToOne
    @MapsId("nutrientId")
    @JoinColumn(name = "nutrient_id")
    Nutrient nutrient;

    long quantity;

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

    public Nutrient getNutrition() {
        return nutrient;
    }

    public void setNutrition(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
