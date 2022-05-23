package com.chefApp.demo.rest;

import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/recipes")
public class RecipeEndpoint {

    @Autowired
    RecipeService service;

    @GetMapping
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        if(id >= 0) {
            Optional<Recipe> foundRecipe = service.getOne(id);
            if(foundRecipe.isPresent()) {
                return new ResponseEntity<>(foundRecipe.get(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Recipe> createNewRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(this.service.createOne(recipe), HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable long id) {
        if (id >= 0) {
            Optional<Recipe> exists = service.getOne(id);
            if (exists.isPresent()) {
                service.deleteOne(id);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
	}
}
