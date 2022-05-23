package com.chefApp.demo.controller;
import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.Recipe;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.ValidationException;

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
    
    public Recipe createOne(Recipe recipe) { return r.save(recipe); }

    public Object updateOne(Recipe newRecipe, long id) {
        Recipe oldRecipe = this.getOne(id).get();
        if (newRecipe.getName().length() > 0) {
            oldRecipe.setName(newRecipe.getName());
        } else {
            return new ValidationException();
        }
        if (newRecipe.getCost() > 0) {
            oldRecipe.setCost(newRecipe.getCost());
        } else {
            return new ValidationException();
        }
        if (newRecipe.getSalePrice() > 0) {
            oldRecipe.setCost(newRecipe.getCost());
        } else {
            return new ValidationException();
        }
        return r.save(oldRecipe);
    }

    public void deleteOne(long id) {
        r.deleteById(id);    
    }
}