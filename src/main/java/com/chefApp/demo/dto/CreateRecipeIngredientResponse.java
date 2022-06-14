package com.chefApp.demo.dto;

import javax.persistence.Column;

public class CreateRecipeIngredientResponse {
    private long RecipeIngredientId;
    private String code;
    private String name;
    private double amount;
    private String metric;
    @Column(name = "grp")
    private String group;
    private double marketPrice;

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
