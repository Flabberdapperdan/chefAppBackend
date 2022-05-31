package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeIngredientService {
    @Autowired
    RecipeIngredientRepository r;

    public List<RecipeIngredient> getByRecipeId(long id) {
        return r.findByRecipeId(id);
    }

}
