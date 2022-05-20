package com.chefApp.demo.controller;

import com.chefApp.demo.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
