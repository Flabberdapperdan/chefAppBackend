package com.chefApp.demo.service;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Recipe;
import com.chefApp.demo.repository.RecipeRepository;

@Service
// @Transactional kan ook hier maar dan worden alle PUBLIC methods automatische transactioneel
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    Logger logger = Logger.getLogger(RecipeService.class.getName());

    public List<Recipe> readAll() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> read(long id){
       return recipeRepository.findById(id);
    }
    
    /*
     * The annotation @Transactional starts a Transaction when
     * - it is a public method
     * - it is invoked from an other class than this class 
     */
    @Transactional
    public Recipe create(Recipe recipe) {
        // Data Access Verification
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe update(Recipe recipe) {
    	// Data Access Verification
   		return recipeRepository.save(recipe);
    }

    @Transactional
    public void delete(long id) {
        recipeRepository.deleteById(id);    
    }
}