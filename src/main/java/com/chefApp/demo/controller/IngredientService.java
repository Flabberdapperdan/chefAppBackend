package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository r;

    public Optional<Ingredient> getOne(long id) {
        Optional<Ingredient> foundIngredient = r.findById(id);
        return foundIngredient;
    }

    public List<Ingredient> getAll() {
        return r.findAll();
    }

    public void createOne(Ingredient ingredient) {
        r.save(ingredient);
    }

    public void deleteOne(long id) {
        r.deleteById(id);
    }
}
