package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.IngredientNutrient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.ValidationException;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientNutrientService {
    @Autowired
    private IngredientNutrientRepository r;

    public List<IngredientNutrient> getAll() {
        return r.findAll();
    }

    public Optional<IngredientNutrient> getOne(long id) {
       return r.findById(id);
    }

    public IngredientNutrient createOne(IngredientNutrient ingredientNutrient) {
        System.out.println(ingredientNutrient);
        return r.save(ingredientNutrient);
    }

    public Object updateOne(IngredientNutrient newIngredientNutrient, long id) {
        newIngredientNutrient.setId(id);
        return r.save(newIngredientNutrient);
    }
    public void deleteOne(long id) {
        r.deleteById(id);
    }
}
