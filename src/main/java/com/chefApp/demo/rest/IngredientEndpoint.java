package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class IngredientEndpoint {

    @Autowired
    IngredientService service;

    @GetMapping("ingredient/{id}")
    public Ingredient getIngredientById(@PathVariable("id") int id) {
        Ingredient foundIngredient = service.getOne(id).get();
        return foundIngredient;
    }

    @PostMapping("createIngredient")
    public List<Ingredient> createNewIngredient(@RequestBody Ingredient ingredient) {
        service.createOne(ingredient);
        return Arrays.asList(ingredient);
    }
}
