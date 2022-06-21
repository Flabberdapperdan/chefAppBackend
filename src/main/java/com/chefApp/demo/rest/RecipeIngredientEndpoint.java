package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.CreateRecipeIngredientRequest;
import com.chefApp.demo.dto.CreateRecipeIngredientResponse;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.RecipeIngredient;
import com.chefApp.demo.service.IngredientService;
import com.chefApp.demo.service.RecipeIngredientService;
import com.chefApp.demo.service.RecipeService;

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
    public List<CreateRecipeIngredientResponse> getIngredientsByRecipeId(@PathVariable long recipeId) {
        List<RecipeIngredient> list = recipeIngredientService.findByRecipeId(recipeId);
        return list.stream().map(entry -> {
            CreateRecipeIngredientResponse dto = new CreateRecipeIngredientResponse();
            Ingredient ingredient = entry.getIngredient();
            dto.setRecipeIngredientId(entry.getId());
            dto.setCode(ingredient.getCode());
            dto.setName(ingredient.getName());
            dto.setAmount(entry.getAmount());
            dto.setMetric(entry.getMetric());
            dto.setGroup(ingredient.getGroup());
            dto.setMarketprice(ingredient.getMarketprice());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("ingredient/{ingredientId}")
    public List<RecipeIngredient> getRecipeIngredientsByIngredientId(@PathVariable long ingredientId) {
        return recipeIngredientService.findByIngredientId(ingredientId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createRecipeIngredient(@RequestBody CreateRecipeIngredientRequest dto) {
    	
    	Optional<Recipe> optionalRecipe = recipeService.read(dto.getRecipeId());
    	if (optionalRecipe.isEmpty())
    		return false;
    	
    	Optional<Ingredient> optionalIngredient = ingredientService.read(dto.getIngredientId());
    	if (optionalIngredient.isEmpty())
    		return false;
    	
    	Recipe recipe = optionalRecipe.get();
    	Ingredient ingredient = optionalIngredient.get();

    	RecipeIngredient recipeIngredient = new RecipeIngredient();
    	recipeIngredient.setRecipe(recipe);
    	recipeIngredient.setIngredient(ingredient);
    	recipeIngredient.setAmount(dto.getAmount());
    	recipeIngredient.setMetric(dto.getMetric());

    	recipeIngredientService.create(recipeIngredient);
    	return true;
    }

    @PutMapping("{id}")
    public boolean updateRecipeIngredient(@PathVariable long id, @RequestBody CreateRecipeIngredientRequest dto) {
        Optional<RecipeIngredient> optionalOldRecipeIngredient = recipeIngredientService.read(id);
        if (optionalOldRecipeIngredient.isPresent()) {
            RecipeIngredient oldRecipeIngredient = optionalOldRecipeIngredient.get();
            // Properties updaten
            oldRecipeIngredient.setAmount(dto.getAmount());
            // Send to service layer
            recipeIngredientService.update(oldRecipeIngredient);
            return true;
        } else {
            return false;
        }
    }

    @DeleteMapping("{id}")
    public boolean deleteRecipeIngredientById(@PathVariable long id) {
        Optional<RecipeIngredient> optionalRecipeIngredient = recipeIngredientService.read(id);
        if (optionalRecipeIngredient.isPresent()) {
            recipeIngredientService.delete(id);
            return true;
        } else {
            return false;
        }
    }

}
