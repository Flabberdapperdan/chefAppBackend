package com.chefApp.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.model.RecipeIngredient;
import com.chefApp.demo.repository.RecipeIngredientRepository;

@Service
public class RecipeIngredientService {
	@Autowired
	RecipeIngredientRepository recipeIngredientRepository;

	Logger logger = Logger.getLogger(RecipeIngredientService.class.getName());

	public List<RecipeIngredient> readAll()
	{
		return recipeIngredientRepository.findAll();
	}

	public Optional<RecipeIngredient> read(long id) {
		return recipeIngredientRepository.findById(id);
	}

	public List<RecipeIngredient> findByRecipeId(long id) {
		return recipeIngredientRepository.findByRecipeId(id);
	}

	public List<RecipeIngredient> findByIngredientId(long id) {
		return recipeIngredientRepository.findByIngredientId(id);
	}

	@Transactional
	public RecipeIngredient create(RecipeIngredient recipeIngredient) {
		//Data Access Verification
		return recipeIngredientRepository.save(recipeIngredient);
	}

	@Transactional
	public RecipeIngredient update(RecipeIngredient recipeIngredient) {
    	// Data Access Verification
   		return recipeIngredientRepository.save(recipeIngredient);
    }

	@Transactional
	public void delete(long id) {
		recipeIngredientRepository.deleteById(id);
	}

}
