package com.chefApp.demo.controller;

import com.chefApp.demo.model.IngredientNutrient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientNutrientRepository extends JpaRepository<IngredientNutrient, Long> {
}
