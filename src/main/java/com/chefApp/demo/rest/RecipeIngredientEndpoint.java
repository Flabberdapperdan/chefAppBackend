package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.RecipeIngredientDTO;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.RecipeIngredient;
import com.chefApp.demo.service.IngredientService;
import com.chefApp.demo.service.RecipeIngredientService;
import com.chefApp.demo.service.RecipeService;

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

    Logger logger = Logger.getLogger(RecipeIngredientEndpoint.class.getName());

    @GetMapping("/recipe/{recipeId}")
    public List<RecipeIngredient> getRecipeIngredientsByRecipeId(@PathVariable long recipeId) {
    	return recipeIngredientService.findByRecipeId(recipeId);
    }

    @GetMapping("ingredient/{ingredientId}")
    public List<RecipeIngredient> getRecipeIngredientsByIngredientId(@PathVariable long ingredientId) {
        return recipeIngredientService.findByIngredientId(ingredientId);
    }

    @PostMapping()
    public ResponseEntity<RecipeIngredient> createRecipeIngredient(@RequestBody RecipeIngredientDTO dto) {
    	
    	Optional<Recipe> optionalRecipe = recipeService.read(dto.getRecipeId());
    	if (optionalRecipe.isEmpty())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    	Optional<Ingredient> optionalIngredient = ingredientService.read(dto.getIngredientId());
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

    	recipeIngredientService.create(recipeIngredient);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public RecipeIngredient deleteRecipeIngredientById(@PathVariable long id) {
        Optional<RecipeIngredient> optionalRecipeIngredient = recipeIngredientService.read(id);
        if (optionalRecipeIngredient.isPresent())
        {
            recipeIngredientService.delete(id);
        }
        return optionalRecipeIngredient.orElse(null);
    }

}
