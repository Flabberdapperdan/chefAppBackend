package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.chefApp.demo.DTO.RecipeIngredientSearchByRecipeDTO;
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
    public List<RecipeIngredientSearchByRecipeDTO> getIngredientsByRecipeId(@PathVariable long recipeId) {
        List<RecipeIngredient> list = recipeIngredientService.findByRecipeId(recipeId);
        return list.stream().map(entry -> {
            RecipeIngredientSearchByRecipeDTO dto = new RecipeIngredientSearchByRecipeDTO();
            Ingredient ingredient = entry.getIngredient();
            dto.setRecipeIngredientId(entry.getId());
            dto.setCode(ingredient.getCode());
            dto.setName(ingredient.getName());
            dto.setGroup(ingredient.getGroup());
            dto.setMarketPrice(ingredient.getMarketPrice());
            return dto;
        }).collect(Collectors.toList());
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
