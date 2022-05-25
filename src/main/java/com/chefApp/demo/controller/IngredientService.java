package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.ValidationException;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository r;

    public List<Ingredient> getAll() {
        return r.findAll();
    }

    public Optional<Ingredient> getOne(long id) {
       return r.findById(id);
    }

    public Ingredient createOne(Ingredient ingredient) {
        return r.save(ingredient);
    }

    public Object updateOne(Ingredient newIngredient, long id) {
        Ingredient oldIngredient = this.getOne(id).get();
        if (newIngredient.getName().length() > 0) {
            oldIngredient.setName(newIngredient.getName());
        } else {
            return new ValidationException();
        }
        if (newIngredient.getCost() > 0) {
            oldIngredient.setCost(newIngredient.getCost());
        } else {
            return new ValidationException();
        }
        return r.save(oldIngredient);
    }
    public void deleteOne(long id) {
        r.deleteById(id);
    }
}
