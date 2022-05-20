package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utilities.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class IngredientEndpoint {

    @Autowired
    IngredientService service;

    @GetMapping("ingredient/{id}")
    public Ingredient getIngredientById(@PathVariable("id") long id) {
        Ingredient foundIngredient = service.getOne(id).get();
        return foundIngredient;
    }

    @GetMapping("allIngredients")
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> allIngredients = service.getAll();
        return allIngredients;
    }

    @PostMapping("createIngredient")
    public List<Ingredient> createNewIngredient(@RequestBody Ingredient ingredient) {
        service.createOne(ingredient);
        return Arrays.asList(ingredient);
    }

    @PostMapping("updateIngredient/{id}")
    public Response updateById(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        Optional<Ingredient> oldIngredient = service.getOne(id);
        if (oldIngredient.isEmpty() == false) {
            service.updateOne(ingredient, id);
            return new Response(true);
        }
        return new Response(false);
    }

    @DeleteMapping("deleteIngredient/{id}")
    public Response deleteIngredientById(@PathVariable("id") long id) {
        if (id >= 0) {
            service.deleteOne(id);
            return new Response(true);
        } else {
            return new Response(false);
        }
    }
}
