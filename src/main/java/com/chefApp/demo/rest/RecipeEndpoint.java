package com.chefApp.demo.rest;

import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
public class RecipeEndpoint {

    @Autowired
    RecipeService service;
    @Autowired
    

    @GetMapping("recipe/{id}")
    public Recipe getRecipeById(@PathVariable("id") int id) {
        Recipe foundRecipe = service.getOne(id).get();
        return foundRecipe;
    }

    @PostMapping("createRecipe")
    public List<Recipe> createNewRecipe(@RequestBody Recipe recipe) {
        service.createOne(recipe);
        return Arrays.asList(recipe);

    

}
}
