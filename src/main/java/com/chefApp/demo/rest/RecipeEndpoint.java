package com.chefApp.demo.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.GetRecipeResponse;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.RecipeIngredient;
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
            BigDecimal cost = BigDecimal.valueOf(0);
            for(RecipeIngredient recipeIngredient : recipe.getRecipeIngredient()) {
                cost = cost.add(recipeIngredient.getIngredient().getMarketprice().multiply(BigDecimal.valueOf(recipeIngredient.getAmount())));
            }
            dto.setCost(cost);
            dto.setSaleprice(recipe.getSaleprice());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public GetRecipeResponse getRecipeById(@PathVariable long id) {
    	Recipe recipe = recipeService.read(id).orElse(null);

        GetRecipeResponse dto = new GetRecipeResponse();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        BigDecimal cost = BigDecimal.valueOf(0);
        for(RecipeIngredient recipeIngredient : recipe.getRecipeIngredient()) {
            cost = cost.add(recipeIngredient.getIngredient().getMarketprice().multiply(BigDecimal.valueOf(recipeIngredient.getAmount())));
        }
        dto.setCost(cost);
        dto.setSaleprice(recipe.getSaleprice());

        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
            oldRecipe.setSaleprice(input.getSaleprice());
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
