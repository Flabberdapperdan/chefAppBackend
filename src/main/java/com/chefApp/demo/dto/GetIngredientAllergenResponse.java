package com.chefApp.demo.dto;

public class GetIngredientAllergenResponse {
    private long id;

    private GetAllergenResponse allergen;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public GetAllergenResponse getAllergen() {
        return allergen;
    }
    public void setAllergen(GetAllergenResponse allergen) {
        this.allergen = allergen;
    }
}
