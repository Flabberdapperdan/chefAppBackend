package com.chefApp.demo.controller;

import com.chefApp.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
