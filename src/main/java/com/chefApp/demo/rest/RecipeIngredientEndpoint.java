package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.controller.RecipeIngredientService;
import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.RecipeIngredient;
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
    @Autowired
    RecipeService recipeService;
    @Autowired
    IngredientService ingredientService;

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
    public ResponseEntity<RecipeIngredient> postRecipeIngredient(@RequestBody List bodyList) {
        System.out.println(bodyList);
        //Recipe recipe = recipeService(bodyObject.getValue("recipe_id"));
        //return new ResponseEntity<>(this.service.createOne(bodyObject), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
