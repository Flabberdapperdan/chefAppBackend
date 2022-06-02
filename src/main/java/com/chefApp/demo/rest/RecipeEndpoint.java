package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Recipe;


@RestController
@RequestMapping("api/recipes")
public class RecipeEndpoint {

    @Autowired
    RecipeService service;

    @GetMapping
    public List<Recipe> getAll() {
    	return service.getAll();
    }

    @GetMapping("{id}")
    public Recipe getRecipeById(@PathVariable long id) {
    	return service.getOne(id).orElse(null);
    	
//        if(id >= 0) {
//            Optional<Recipe> foundRecipe = service.getOne(id);
//            if(foundRecipe.isPresent()) {
//                return new ResponseEntity<>(foundRecipe.get(), HttpStatus.ACCEPTED);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    @PostMapping
    public Recipe createNewRecipe(@RequestBody Recipe recipe) {
        return this.service.create(recipe);
    }

    @PutMapping("{id}")
    public boolean updateById(@PathVariable long id, @RequestBody Recipe input) {
        Optional<Recipe> optionalOldRecipe = this.service.getOne(id);
        if (optionalOldRecipe.isPresent()) {
        	Recipe oldRecipe = optionalOldRecipe.get();
        	// Properties updaten
            oldRecipe.setCost(input.getCost());
            oldRecipe.setName(input.getName());
            oldRecipe.setSalePrice(input.getSalePrice());
            // Send to service layer
            this.service.update(oldRecipe);
            
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
        Optional<Recipe> optionalRecipe = service.getOne(id);
        if (optionalRecipe.isPresent()) {
            service.deleteOne(id);
        }
        return optionalRecipe.orElse(null);
	}

}
