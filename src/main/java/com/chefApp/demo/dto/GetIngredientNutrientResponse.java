package com.chefApp.demo.dto;

public class GetIngredientNutrientResponse extends GetNutrientResponse {
    private long joinId;
    private double quantity;

    public long getJoinId() {
        return joinId;
    }
    public void setJoinId(long joinId) {
        this.joinId = joinId;
    }

    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
