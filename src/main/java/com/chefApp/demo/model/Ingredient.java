package com.chefApp.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Ingredient {

    // primary key    AutoIncrement
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String name;
    @Column(name = "grp")
    private String group;
    private double marketPrice;

    //link to RecipeIngredient
    @OneToMany(mappedBy = "ingredient")
    List<RecipeIngredient> recipeIngredient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public List<RecipeIngredient> getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(List<RecipeIngredient> recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }
}
