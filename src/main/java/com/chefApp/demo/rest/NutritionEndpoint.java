package com.chefApp.demo.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chefApp.demo.controller.NutritionService;
import com.chefApp.demo.model.Nutrition;

public class NutritionEndpoint {
    @Autowired
    NutritionService service;

    @GetMapping("{id}")
    public Nutrition getNutritionById(@PathVariable() long id) {
        Nutrition foundNutrition = service.getOne(id).get();
        return foundNutrition;
    }

    @GetMapping
    public List<Nutrition> getAllNutrition() {
        List<Nutrition> allNutrition = service.getAll();
        return allNutrition;
    }

    @PostMapping
    public List<Nutrition> createNewNutrition(@RequestBody Nutrition nutrition) {
        service.createOne(nutrition);
        return Arrays.asList(nutrition);
    }
//    @PutMapping("{id}")
    
    @DeleteMapping("deleteNutrition/{id}")
    public void deleteNutritionById(@PathVariable("id") long id) {
        service.deleteOne(id);
    }
}
