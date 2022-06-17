package com.chefApp.demo.dto;

import java.math.BigDecimal;

public class UpdateIngredientRequest {
    private long code;
    private String name;
    private String group;
    private BigDecimal marketprice;

    //getters and setters
    public long getCode() {
        return code;
    }
    public void setCode(long code) {
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
    public BigDecimal getMarketprice() {
        return marketprice;
    }
    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }
}
