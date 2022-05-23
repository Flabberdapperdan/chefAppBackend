package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional foundOptionalIngredient = service.getOne(id);
        Ingredient foundIngredient = new Ingredient();
        if (foundOptionalIngredient.isPresent()) {
            foundIngredient = (Ingredient) foundOptionalIngredient.get();
        }

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

    @PutMapping("updateIngredient/{id}")
    public Response updateById(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        Optional<Ingredient> oldIngredient = service.getOne(id);
        if (oldIngredient.isEmpty() == false) {
            service.updateOne(ingredient, id);
            return new Response(true);
        }
        return new Response(false);
    }

    @DeleteMapping("deleteIngredient/{id}")
    public void deleteIngredientById(@PathVariable("id") long id) {
        service.deleteOne(id);
    }

}

        /*
         if (id >= 0) {
            service.deleteOne(id);
            return new Response(true);
        } else {
            return new Response(false);
        } */



