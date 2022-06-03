package com.chefApp.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class GetIngredientResponse {
    private long id;
    private String code;
    private String name;
    private String group;
    private double marketPrice;

    @JsonInclude(Include.NON_NULL)
 	private List<GetIngredientNutrientResponse> nutrients;
    @JsonInclude(Include.NON_NULL)
    private List<GetIngredientAllergenResponse> allergens;

    //getters and setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public String getGroup()
    {
        return group;
    }
    public void setGroup(String group)
    {
        this.group = group;
    }
    public double getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }
    public List<GetIngredientNutrientResponse> getNutrients() {
        return nutrients;
    }
    public void setNutrients(List<GetIngredientNutrientResponse> nutrients) {
        this.nutrients = nutrients;
    }
    public List<GetIngredientAllergenResponse> getAllergens() {
        return allergens;
    }
    public void setAllergens(List<GetIngredientAllergenResponse> allergens) {
        this.allergens = allergens;
    }
}
