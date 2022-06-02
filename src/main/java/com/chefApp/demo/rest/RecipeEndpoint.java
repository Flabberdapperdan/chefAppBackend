package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.chefApp.demo.DTO.RecipeSendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.service.RecipeService;


@RestController
@RequestMapping("api/recipes")
public class RecipeEndpoint {

    @Autowired
    RecipeService recipeService;

    Logger logger = Logger.getLogger(RecipeEndpoint.class.getName());

    @GetMapping
    public List<Recipe> getAllRecipes() {
    	return recipeService.readAll();

        //this still needs the DTO!
    }

    @GetMapping("{id}")
    public RecipeSendDTO getRecipeById(@PathVariable long id) {
    	Recipe recipe = recipeService.read(id).orElse(null);

        RecipeSendDTO dto = new RecipeSendDTO();
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
        if (optionalOldRecipe.isPresent())
        {
        	Recipe oldRecipe = optionalOldRecipe.get();
        	// Properties updaten
            oldRecipe.setCost(input.getCost());
            oldRecipe.setName(input.getName());
            oldRecipe.setSalePrice(input.getSalePrice());
            // Send to service layer
            recipeService.update(oldRecipe);
            
            return true;
        }
        return false;
    }

//    Je kan ook via recipe bij ingredients
//    @GetMapping("{id}/ingredients")
//    public ResponseEntity<RecipeIngredient> getRecipeIngredients(@PathVariable long id) {
//    }

    @DeleteMapping("{id}")
    public Recipe deleteRecipeById(@PathVariable long id) {
        Optional<Recipe> optionalRecipe = recipeService.read(id);
        if (optionalRecipe.isPresent())
        {
            recipeService.delete(id);
        }
        return optionalRecipe.orElse(null);
	}

}
