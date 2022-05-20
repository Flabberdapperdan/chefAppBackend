package com.chefApp.demo.controller;

import com.chefApp.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository r;
    
    public void getOne(long id) {
        Recipe foundRecipe = r.findById(id);
    }
    public void createOne(Recipe recipe) {
        r.save(recipe);
    }
    public Recipe safeOne(Recipe recipe) {
        return recipe;
    }
}
