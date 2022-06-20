package com.chefApp.demo.service;

import com.chefApp.demo.model.IngredientNutrient;
import com.chefApp.demo.repository.IngredientNutrientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import java.util.Optional;

@Service
public class IngredientNutrientService {
    @Autowired
    private IngredientNutrientRepository ingredientNutrientRepository;

    Logger logger = Logger.getLogger(IngredientNutrientService.class.getName());

    public List<IngredientNutrient> readAll() {
        return ingredientNutrientRepository.findAll();
    }

    public Optional<IngredientNutrient> read(long id) {
       return ingredientNutrientRepository.findById(id);
    }

    public List<IngredientNutrient> findByIngredientId(long id) {
		return ingredientNutrientRepository.findByIngredientId(id);
	}

    @Transactional
    public IngredientNutrient create(IngredientNutrient ingredientNutrient) {
        //Data Access Verification
        return ingredientNutrientRepository.save(ingredientNutrient);
    }

    @Transactional
    public IngredientNutrient update(IngredientNutrient ingredientNutrient) {
        //Data Access Verification
        return ingredientNutrientRepository.save(ingredientNutrient);
    }

    @Transactional
    public void delete(long id) {
        ingredientNutrientRepository.deleteById(id);
    }
}
