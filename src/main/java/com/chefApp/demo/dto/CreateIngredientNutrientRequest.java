package com.chefApp.demo.dto;

public class CreateIngredientNutrientRequest {
    private long nutrientId;
    private double quantity;

    public long getNutrientId() {
        return nutrientId;
    }
    public void setNutrientId(long nutrientId) {
        this.nutrientId = nutrientId;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
