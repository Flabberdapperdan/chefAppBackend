package com.chefApp.demo.DTO;

import javax.persistence.Column;

public class RecipeIngredientSearchByRecipeDTO {
    private long RecipeIngredientId;
    private String code;
    private String name;
    @Column(name = "grp")
    private String group;
    private double marketPrice;

    public long getRecipeIngredientId() {
        return RecipeIngredientId;
    }

    public void setRecipeIngredientId(long recipeIngredientId) {
        RecipeIngredientId = recipeIngredientId;
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
}
