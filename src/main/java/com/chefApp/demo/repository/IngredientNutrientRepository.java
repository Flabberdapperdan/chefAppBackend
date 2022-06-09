package com.chefApp.demo.repository;

import com.chefApp.demo.model.IngredientNutrient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientNutrientRepository extends JpaRepository<IngredientNutrient, Long> {

    List<IngredientNutrient> findByIngredientId(long id);
    
}
