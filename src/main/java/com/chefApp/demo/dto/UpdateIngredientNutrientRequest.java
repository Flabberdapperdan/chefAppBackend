package com.chefApp.demo.dto;

public class UpdateIngredientNutrientRequest {
    private long id;
    private double quantity;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
