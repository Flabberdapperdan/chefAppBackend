package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class IngredientEndpoint {

    @Autowired
    IngredientService service;

    @GetMapping("ingredient/{id}")
    public Ingredient getIngredientById(@PathVariable("id") long id) {
        Ingredient foundIngredient = service.getOne(id).get();
        return foundIngredient;
    }

    @GetMapping("allIngredients")
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> allIngredients = service.getAll();
        return allIngredients;
    }

    @PostMapping("createIngredient")
    public List<Ingredient> createNewIngredient(@RequestBody Ingredient ingredient) {
        service.createOne(ingredient);
        return Arrays.asList(ingredient);
    }

    @DeleteMapping("deleteIngredient/{id}")
    public void deleteIngredientById(@PathVariable("id") long id) {
        service.deleteOne(id);
    }
}
