package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository r;

    public void getOne(int id) {
        Ingredient foundIngredient = r.findById(id);
    }

    public void createOne(Ingredient ingredient) {
        r.save(ingredient);
    }
}
