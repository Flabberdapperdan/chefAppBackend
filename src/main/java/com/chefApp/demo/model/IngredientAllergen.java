package com.chefApp.demo.model;

import javax.persistence.*;

@Entity
public class IngredientAllergen {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    @ManyToOne
    @MapsId("allergenId")
    @JoinColumn(name = "allergen_id")
    Allergen allergen;

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
    public Allergen getAllergen() {
        return allergen;
    }
    public void setAllergen(Allergen allergen) {
        this.allergen = allergen;
    }
}
