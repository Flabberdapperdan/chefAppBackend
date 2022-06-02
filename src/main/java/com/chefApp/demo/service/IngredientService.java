package com.chefApp.demo.service;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.repository.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    Logger logger = Logger.getLogger(IngredientService.class.getName());

    public List<Ingredient> readAll() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> read(long id) {
       return ingredientRepository.findById(id);
    }

    public Ingredient create(Ingredient ingredient) {
        //Data Access Verification
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        //Data Access Verification
        return ingredientRepository.save(ingredient);
    }
    public void delete(long id) {
        ingredientRepository.deleteById(id);
    }
}
