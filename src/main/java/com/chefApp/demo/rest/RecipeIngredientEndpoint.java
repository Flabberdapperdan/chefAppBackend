package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chefApp.demo.DTO.RecipeIngredientDTO;
import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.controller.RecipeIngredientService;
import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.RecipeIngredient;

@RestController
@RequestMapping("api/recipeIngredient")
public class RecipeIngredientEndpoint {

    @Autowired
    RecipeService recipeService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    RecipeIngredientService recipeIngredientService;

    @GetMapping("/recipe/{recipeId}")
    public List<RecipeIngredient> getRecipeIngredientsByRecipeId(@PathVariable long recipeId) {
    	return this.recipeIngredientService.getByRecipeId(recipeId);
    }

    @GetMapping("ingredient/{ingredientId}")
    public List<RecipeIngredient> getRecipeIngredientsByIngredientId(@PathVariable long ingredientId) {
        return this.recipeIngredientService.getByIngredientId(ingredientId);
    }

    @PostMapping()
    public ResponseEntity<RecipeIngredient> postRecipeIngredient(@RequestBody RecipeIngredientDTO dto) {
    	
    	Optional<Recipe> optionalRecipe = this.recipeService.getOne(dto.getRecipeId());
    	if (optionalRecipe.isEmpty())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    	Optional<Ingredient> optionalIngredient = this.ingredientService.getOne(dto.getIngredientId());
    	if (optionalIngredient.isEmpty())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    	Recipe recipe = optionalRecipe.get();
    	Ingredient ingredient = optionalIngredient.get();

    	RecipeIngredient recipeIngredient = new RecipeIngredient();
    	recipeIngredient.setRecipe(recipe);
    	recipeIngredient.setIngredient(ingredient);
    	recipeIngredient.setAmount(dto.getAmount());
    	recipeIngredient.setMetric(dto.getMetric());

    	this.recipeIngredientService.create(recipeIngredient);
    	return new ResponseEntity<>(HttpStatus.OK);
    }


}
