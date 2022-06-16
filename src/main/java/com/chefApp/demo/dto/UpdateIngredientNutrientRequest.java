package com.chefApp.demo.dto;

public class UpdateIngredientNutrientRequest {
    private long joinId;
    private long nutrientId;
    private double quantity;

    public long getJoinId() {
        return joinId;
    }
    public void setJoinId(long joinId) {
        this.joinId = joinId;
    }
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
