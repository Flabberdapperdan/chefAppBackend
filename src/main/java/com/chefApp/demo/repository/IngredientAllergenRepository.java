package com.chefApp.demo.repository;

import com.chefApp.demo.model.IngredientAllergen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientAllergenRepository extends JpaRepository<IngredientAllergen, Long> {
    
    List<IngredientAllergen> findByIngredientId(long id);
    
}
