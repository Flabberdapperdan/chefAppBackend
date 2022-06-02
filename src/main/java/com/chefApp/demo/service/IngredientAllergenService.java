package com.chefApp.demo.service;

import com.chefApp.demo.model.IngredientAllergen;
import com.chefApp.demo.repository.IngredientAllergenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.Optional;

@Service
public class IngredientAllergenService {
    @Autowired
    private IngredientAllergenRepository ingredientAllergenRepository;

    Logger logger = Logger.getLogger(IngredientAllergenService.class.getName());

    public List<IngredientAllergen> readAll() {
        return ingredientAllergenRepository.findAll();
    }

    public Optional<IngredientAllergen> read(long id) {
       return ingredientAllergenRepository.findById(id);
    }

    public IngredientAllergen create(IngredientAllergen ingredientAllergen) {
        //Data Access Verification
        return ingredientAllergenRepository.save(ingredientAllergen);
    }

    public IngredientAllergen update(IngredientAllergen ingredientAllergen) {
        //Data Access Verification
        return ingredientAllergenRepository.save(ingredientAllergen);
    }
    public void delete(long id) {
        ingredientAllergenRepository.deleteById(id);
    }
}
