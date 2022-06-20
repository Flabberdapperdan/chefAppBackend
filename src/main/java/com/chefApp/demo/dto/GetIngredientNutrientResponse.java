package com.chefApp.demo.dto;

public class GetIngredientNutrientResponse {
    private long id;
    private double quantity;

    private GetNutrientResponse nutrient;

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

    public GetNutrientResponse getNutrient() {
        return nutrient;
    }
    public void setNutrient(GetNutrientResponse nutrient) {
        this.nutrient = nutrient;
    }
}
