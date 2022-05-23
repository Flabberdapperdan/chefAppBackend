package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ingredients")
public class IngredientEndpoint {

    @Autowired
    private IngredientService service;

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> allIngredients = service.getAll();
        return allIngredients;
    }

    @GetMapping("{id}")
    public Ingredient getIngredientById(@PathVariable long id) {
        Ingredient foundIngredient = service.getOne(id).get();
        return foundIngredient;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createNewIngredient(@RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(this.service.createOne(ingredient), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Ingredient> updateById(@PathVariable long id, @RequestBody Ingredient input) {
        Optional<Ingredient> oldIngredient = this.service.getOne(input.getId());
        Ingredient newIngredient = input;
        if (oldIngredient.isEmpty() == false) {
            Ingredient updated = (Ingredient) this.service.updateOne(newIngredient, oldIngredient.get().getId());
            return new ResponseEntity<>(updated, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Ingredient(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteIngredientById(@PathVariable("id") long id) {
        if (id >= 0) {
            service.deleteOne(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}



