package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.DTO.RecipeIngredientDTO;
import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.controller.RecipeIngredientService;
import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.RecipeIngredient;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

@RestController
@RequestMapping("api/recipe-ingredient")
public class RecipeIngredientEndpoint {

    @Autowired
    RecipeService recipeService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    RecipeIngredientService recipeIngredientService;

    @GetMapping("/recipe/{recipeId}")
    public List<RecipeIngredient> getRecipeIngredientsByRecipeId(@PathVariable long recipeId) {
        System.out.println(this.recipeIngredientService.getByRecipeId(recipeId));
    	return this.recipeIngredientService.getByRecipeId(recipeId);
    }

    @GetMapping("ingredient/{ingredientId}")
    public List<RecipeIngredient> getRecipeIngredientsByIngredientId(@PathVariable long ingredientId) {
        return this.recipeIngredientService.getByIngredientId(ingredientId);
    }

    @PostMapping()
    public ResponseEntity<RecipeIngredient> postRecipeIngredient(@RequestBody RecipeIngredientDTO dto) {
    	System.out.println("we are posting recipeIngredient");
        System.out.println(dto.getIngredientId());
        System.out.println(dto.getRecipeId());

    	Optional<Recipe> optionalRecipe = this.recipeService.getOne(dto.getRecipeId());
    	if (optionalRecipe.isEmpty())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    	Optional<Ingredient> optionalIngredient = this.ingredientService.getOne(dto.getIngredientId());
    	if (optionalIngredient.isEmpty())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    	Recipe recipe = optionalRecipe.get();
    	Ingredient ingredient = optionalIngredient.get();

        System.out.println("and still posting");
    	RecipeIngredient recipeIngredient = new RecipeIngredient();
    	recipeIngredient.setRecipe(recipe);
    	recipeIngredient.setIngredient(ingredient);
    	recipeIngredient.setAmount(dto.getAmount());
    	recipeIngredient.setMetric(dto.getMetric());

    	this.recipeIngredientService.create(recipeIngredient);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public RecipeIngredient deleteRecipeIngredient(@PathVariable long id) {
        Optional<RecipeIngredient> optionalRecipeIngredient = recipeIngredientService.getOne(id);
        if (optionalRecipeIngredient.isPresent()) {
            recipeIngredientService.delete(id);
        }
        return optionalRecipeIngredient.orElse(null);
    }

}
