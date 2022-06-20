package com.chefApp.demo.dto;

import java.util.List;

public class GetNutrientPageResponse {
    private List<GetNutrientResponse> nutrients;

    private int currentPage;
    private int totalPages;
    private long totalItems;

    //getters and setters
    public List<GetNutrientResponse> getNutrients(){
        return nutrients;
    }
    public void setNutrients(List<GetNutrientResponse> nutrients)
    {
        this.nutrients = nutrients;
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
