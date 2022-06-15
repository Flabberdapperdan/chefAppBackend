package com.chefApp.demo.model;

import javax.persistence.*;

public class UserIngredient {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    long id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "use_id")
    User user;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    double cost;

    // getters and setters \\
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
