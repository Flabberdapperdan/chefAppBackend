package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;

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
    
    // let op: deze MOET bovenin andere krijg je een clash met @GetMapping("{id}")
    @GetMapping("expensive")
    public ResponseEntity<List<Ingredient>> findExpensive() {
    	return new ResponseEntity<>(this.service.findExpensive(), HttpStatus.OK);
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
    
    @GetMapping("byName/{name}")
    public ResponseEntity<Ingredient> findByName(@PathVariable String name) {
    	
    	Optional<Ingredient> optionalIngredient = this.service.findByName(name);
    	if(optionalIngredient.isPresent()) {
    		return new ResponseEntity<Ingredient>(optionalIngredient.get(), HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
    	}
    }
}



