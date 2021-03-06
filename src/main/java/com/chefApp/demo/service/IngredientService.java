package com.chefApp.demo.service;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.repository.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    Logger logger = Logger.getLogger(IngredientService.class.getName());

    public Page<Ingredient> getAll(Pageable pageable) {
        return ingredientRepository.findAll(pageable);
    }

    public Optional<Ingredient> read(long id) {
       return ingredientRepository.findById(id);
    }

    @Transactional
    public Ingredient create(Ingredient ingredient) {
        //Data Access Verification
        return ingredientRepository.save(ingredient);
    }

    @Transactional
    public Ingredient update(Ingredient ingredient) {
        //Data Access Verification
        return ingredientRepository.save(ingredient);
    }

    @Transactional
    public void delete(long id) {
        ingredientRepository.deleteById(id);
    }
}
