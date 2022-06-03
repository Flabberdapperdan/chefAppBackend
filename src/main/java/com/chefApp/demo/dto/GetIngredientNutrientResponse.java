package com.chefApp.demo.dto;

public class GetIngredientNutrientResponse extends GetNutrientResponse {
    private long quantity;

    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
