package com.chefApp.demo.controller;

import com.chefApp.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository r;

    public Recipe safeOne(Recipe recipe) {
        return recipe;
    }
}
