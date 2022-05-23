package com.chefApp.demo.controller;
import com.chefApp.demo.model.Recipe;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public void createOne(Recipe recipe) {
        r.save(recipe);
        
    }    
    
    public void deleteOne(long id) {
        r.deleteById(id);    
    }
}