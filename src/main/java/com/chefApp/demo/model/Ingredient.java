package com.chefApp.demo.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Ingredient {

    // primary key    AutoIncrement
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double cost;
    @ManyToMany
    @JoinTable(
            name = "ingredient_allergens",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id"))
    Set<Allergen> containedAllergens;

    //link to RecipeIngredient
    @OneToMany(mappedBy = "ingredient")
    Set<RecipeIngredient> recipeIngredient;

    //getters and setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public Set<Allergen> getContainedAllergens() {
        return containedAllergens;
    }

    public void setContainedAllergens(Set<Allergen> containedAllergens) {
        this.containedAllergens = containedAllergens;
    }
}
