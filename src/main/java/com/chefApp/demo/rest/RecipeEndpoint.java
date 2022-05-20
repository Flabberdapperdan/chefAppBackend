package com.chefApp.demo.rest;

import com.chefApp.demo.controller.RecipeService;
import com.chefApp.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
public class RecipeEndpoint {

    @Autowired
    RecipeService service;

    @GetMapping("getAll")
    public List<Recipe> firstGet() {
        System.out.println("the first get is here");
        Recipe r = new Recipe();
        r.setId(1);
        r.setName("boerenkool");
        r.setWeight(100);
        return Arrays.asList(r);
    }

    @PostMapping("create")
    public void createRecipe(@RequestBody Recipe recipe) {

    }
}
