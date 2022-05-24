package com.chefApp.demo.rest;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ingredients")
public class IngredientEndpoint {

    @Autowired
    private IngredientService service;

    @GetMapping
    public ResponseEntity<List> getAllIngredients() {
        List<Ingredient> allIngredients = service.getAll();
        if (!allIngredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(allIngredients, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id) {
        if (id >= 0) {
            Optional<Ingredient> found = service.getOne(id);
            if (found.isPresent()) {
                return new ResponseEntity<>(found.get(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Ingredient> createNewIngredient(@RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(this.service.createOne(ingredient), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Ingredient> updateById(@PathVariable long id, @RequestBody Ingredient input) {
        Optional<Ingredient> oldIngredient = this.service.getOne(id);
        if (oldIngredient.isPresent()) {
            if (oldIngredient.isEmpty() == false) {
                Ingredient updated = (Ingredient) this.service.updateOne(input, id);
                return new ResponseEntity<>(updated, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteIngredientById(@PathVariable("id") long id) {
        if (id >= 0) {
            Optional<Ingredient> exists = service.getOne(id);
            if (exists.isPresent()) {
                service.deleteOne(id);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}



