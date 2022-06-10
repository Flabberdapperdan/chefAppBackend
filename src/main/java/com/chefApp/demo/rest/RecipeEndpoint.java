package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.GetRecipeResponse;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.service.RecipeService;


@RestController
@RequestMapping("api/recipes")
public class RecipeEndpoint {

    @Autowired
    RecipeService recipeService;

    @GetMapping
    public List<GetRecipeResponse> getAllRecipes() {
    	List<Recipe> list = recipeService.readAll();
        return list.stream().map(recipe -> {
            GetRecipeResponse dto = new GetRecipeResponse();
            dto.setId(recipe.getId());
            dto.setUserId(recipe.getUserId());
            dto.setName(recipe.getName());
            dto.setCost(recipe.getCost());
            dto.setSalePrice(recipe.getSalePrice());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public GetRecipeResponse getRecipeById(@PathVariable long id) {
    	Recipe recipe = recipeService.read(id).orElse(null);

        GetRecipeResponse dto = new GetRecipeResponse();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setCost(recipe.getCost());
        dto.setSalePrice(recipe.getSalePrice());

        return dto;
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.create(recipe);
    }

    @PutMapping("{id}")
    public boolean updateRecipeById(@PathVariable long id, @RequestBody Recipe input) {
        Optional<Recipe> optionalOldRecipe = recipeService.read(id);
        if (optionalOldRecipe.isPresent()) {
        	Recipe oldRecipe = optionalOldRecipe.get();
        	// Properties updaten
            oldRecipe.setCost(input.getCost());
            oldRecipe.setName(input.getName());
            oldRecipe.setSalePrice(input.getSalePrice());
            // Send to service layer
            recipeService.update(oldRecipe);
            
            return true;
        } else {
            return false;
        }
    }

//    Je kan ook via recipe bij ingredients
//    @GetMapping("{id}/ingredients")
//    public ResponseEntity<RecipeIngredient> getRecipeIngredients(@PathVariable long id) {
//    }

    @DeleteMapping("{id}")
    public Boolean deleteRecipeById(@PathVariable long id) {
        Optional<Recipe> optionalRecipe = recipeService.read(id);
        if (optionalRecipe.isPresent()) {
            recipeService.delete(id);
            return true;
        } else {
            return false;
        }
	}

}
