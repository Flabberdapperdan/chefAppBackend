package com.chefApp.demo.rest;

import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class IngredientEndpoint {

    @Autowired
    RecipeService service;

    @GetMapping("ingredient/{id}")
    public void getIngredientById(@PathVariable("id") int id) {


    }

    @PostMapping("ingredient")
    public List<Ingredient> createNewIngredient() {
        System.out.println("post ingredient");
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("boerenkool");
        return Arrays.asList(ingredient);
    }
}
