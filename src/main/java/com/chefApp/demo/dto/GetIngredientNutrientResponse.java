package com.chefApp.demo.dto;

public class GetIngredientNutrientResponse extends GetNutrientResponse {
    private double quantity;

    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
