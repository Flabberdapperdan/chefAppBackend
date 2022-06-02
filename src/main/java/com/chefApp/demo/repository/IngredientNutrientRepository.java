package com.chefApp.demo.repository;

import com.chefApp.demo.model.IngredientNutrient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientNutrientRepository extends JpaRepository<IngredientNutrient, Long> {
    
}
