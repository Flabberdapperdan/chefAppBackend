package com.chefApp.demo.dto;

public class CreateIngredientRequest {
    private String code;
    private String name;
    private String group;
    private double marketPrice;

    //getters and setters
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
}
