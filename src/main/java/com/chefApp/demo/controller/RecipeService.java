package com.chefApp.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Recipe;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository r;

    public List<Recipe> getAll() {
        return r.findAll();
    }

    public Optional<Recipe> getOne(long id){
       return r.findById(id);
    }
    
    public Recipe create(Recipe recipe) { return r.save(recipe); }

    public Recipe update(Recipe newRecipe) {
    	// DataAccessException
    	
   		return r.save(newRecipe);
    }

    public void deleteOne(long id) {
        r.deleteById(id);    
    }
}