package com.chefApp.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.RecipeIngredient;

@Service
public class RecipeIngredientService {
	@Autowired
	RecipeIngredientRepository r;

	public Optional<RecipeIngredient> getOne(long id) {
		return r.findById(id);
	}

	public List<RecipeIngredient> getByRecipeId(long id) {
		return r.findByRecipeId(id);
	}

	public List<RecipeIngredient> getByIngredientId(long id) {
		return r.getByIngredientId(id);
	}
	public RecipeIngredient create(RecipeIngredient RecipeIngredient) {
		return r.save(RecipeIngredient);
	}

	public void delete(long id) {
		r.deleteById(id);
	}

}
