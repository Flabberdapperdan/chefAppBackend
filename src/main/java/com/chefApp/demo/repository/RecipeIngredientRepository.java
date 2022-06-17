package com.chefApp.demo.repository;

import com.chefApp.demo.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    List<RecipeIngredient> findByRecipeId(long id);

    List<RecipeIngredient> findByIngredientId(long id);

}
