package com.chefApp.demo.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Entity
public class Ingredient {

    // primary key    AutoIncrement
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private long code;
    private String name;
    @Column(name = "grp")
    private String group;
    @Column(name = "market_price")
    private BigDecimal marketprice;

    //link to RecipeIngredient
    @OneToMany(mappedBy = "ingredient")
    List<RecipeIngredient> recipeIngredient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public List<RecipeIngredient> getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(List<RecipeIngredient> recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }
}
