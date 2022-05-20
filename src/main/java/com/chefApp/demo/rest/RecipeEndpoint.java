package com.chefApp.demo.rest;

import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.Recipe;

import mei.overmorgen.def.domein.Recept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
public class RecipeEndpoint {

    @Autowired
    RecipeService service;
    @Autowired
    

    @GetMapping("getAllRecipes")
    public List<Recipe> firstGet() {
        System.out.println("the first get is here");
        Recipe r = new Recipe();
        r.setId(1);
        r.setName("pasta");
        return Arrays.asList(r);
    }

    @PostMapping("createRecipe")
    @PostMapping("postmetobject")
	public void groen(@RequestBody Recipe r) {
        System.out.println("hij post het nog eenkeer!"+ r.getName());
        service.save(r);
    
//    public List<Recipe> createNewRecipe() {
//    	System.out.println("post recipe");
//        Recipe recipe = new Recipe();
//        recipe.setId(1);
//        recipe.setName("hamburgertje");
//        return Arrays.asList(recipe);
//    }
}
