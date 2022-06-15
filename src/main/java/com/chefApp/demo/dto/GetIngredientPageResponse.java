package com.chefApp.demo.dto;

import java.util.List;

public class GetIngredientPageResponse {
    private List<GetIngredientResponse> ingredients;

    private int currentPage;
    private int totalPages;
    private long totalItems;

    //getters and setters
    public List<GetIngredientResponse> getIngredients(){
        return ingredients;
    }
    public void setIngredients(List<GetIngredientResponse> ingredients)
    {
        this.ingredients = ingredients;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public long getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}
