package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/ingredients")
public class IngredientEndpoint {

    @Autowired
    private IngredientService service;

    @GetMapping("{id}")
    public Ingredient getIngredientById(@PathVariable("id") long id) {
        Ingredient foundIngredient = service.getOne(id).get();
        return foundIngredient;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> allIngredients = service.getAll();
        return allIngredients;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createNewIngredient(@RequestBody Ingredient ingredient) {
      
    	Ingredient saved = this.service.createOne(ingredient);
    	
    	
    	
    	return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    @PutMapping("{id}")
    public Ingredient updateById(@PathVariable long id, @RequestBody Ingredient input) {
    	
    	// pseudo
    	/*
    	 *  Haal ingredient met id id op
    	 *  Zet waarden van input in dat object
    	 *  save dat object
    	 *  return dat
    	 */
    	
    	return null; // foei
    }

    @DeleteMapping("{id}")
    public void deleteIngredientById(@PathVariable("id") long id) {
        service.deleteOne(id);
    }
}
