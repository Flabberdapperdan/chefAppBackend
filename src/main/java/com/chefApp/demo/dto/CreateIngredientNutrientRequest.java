package com.chefApp.demo.dto;

public class CreateIngredientNutrientRequest {
    private long nutrientId;
    private long quantity;

    public long getNutrientId() {
        return nutrientId;
    }
    public void setNutrientId(long nutrientId) {
        this.nutrientId = nutrientId;
    }
    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
