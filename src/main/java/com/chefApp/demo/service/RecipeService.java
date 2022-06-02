package com.chefApp.demo.service;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    Logger logger = Logger.getLogger(RecipeService.class.getName());

    public List<Recipe> readAll() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> read(long id){
       return recipeRepository.findById(id);
    }
    
    public Recipe create(Recipe recipe) {
        // Data Access Verification
        return recipeRepository.save(recipe);
    }

    public Recipe update(Recipe recipe) {
    	// Data Access Verification
   		return recipeRepository.save(recipe);
    }

    public void delete(long id) {
        recipeRepository.deleteById(id);    
    }
}