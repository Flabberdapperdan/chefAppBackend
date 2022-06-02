package com.chefApp.demo.repository;

import com.chefApp.demo.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    
}

