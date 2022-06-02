package com.chefApp.demo.repository;

import com.chefApp.demo.model.IngredientAllergen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientAllergenRepository extends JpaRepository<IngredientAllergen, Long> {
    
}
