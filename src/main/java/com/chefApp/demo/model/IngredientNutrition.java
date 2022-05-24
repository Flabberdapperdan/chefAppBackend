package com.chefApp.demo.model;

import javax.persistence.*;

@Entity
public class IngredientNutrition {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @ManyToMany
    @MapsId("ingredientId")
    @JoinColumn(name = ingredient_id)
    Ingredient ingredient;

    @ManyToMany
    @MapsId("nutritionId")
    @JoinColumn(name = "nutrition_id")
    Nutrition nutrition;
}
