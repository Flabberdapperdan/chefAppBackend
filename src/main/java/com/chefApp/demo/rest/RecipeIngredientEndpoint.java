package com.chefApp.demo.rest;

import com.chefApp.demo.controller.RecipeIngredientService;
import com.chefApp.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipeIngredient")
public class RecipeIngredientEndpoint {
    @Autowired
    RecipeIngredientService service;

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List> getRecipes(@PathVariable long recipeId) {
        if (recipeId >= 0) {
            List responseList = service.getByRecipeId(recipeId);
            if (!responseList.isEmpty()) {
                return new ResponseEntity<List>(responseList, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("ingredient/{ingredientId}")
    public void getIngredients(@PathVariable long ingredientId) {

    }

    @PostMapping
    public void postRecipeIngredient() {
        return new ResponseEntity<>(this.service. (recipe), HttpStatus.CREATED);

    }

}
