package com.chefApp.demo.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.controller.NutritionService;
import com.chefApp.demo.model.Nutrition;

@RestController
@RequestMapping
public class NutritionEndpoint {
    @Autowired
    NutritionService service;

    @GetMapping("{id}")
    public Nutrition getNutritionById(@PathVariable() long id) {
        Nutrition foundNutrition = service.getOne(id).get();
        return foundNutrition;
    }

    @GetMapping
    public List<Nutrition> getAllNutrition() {
        List<Nutrition> allNutrition = service.getAll();
        return allNutrition;
    }

    @PostMapping
    public ResponseEntity<Nutrition> createNewNutrition(@RequestBody Nutrition nutrition) {
    	return new ResponseEntity<Nutrition>(this.service.createOne(nutrition), HttpStatus.CREATED);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Nutrition> updateById(@PathVariable() long id, @RequestBody Nutrition input){
    	Nutrition newNutrition = input;
    	Optional<Nutrition> oldNutrition = this.service.getOne(input.getId());
    	if (oldNutrition.isEmpty()==false) {
    		Nutrition updated = (Nutrition) this.service.updateOne(newNutrition, oldNutrition.get().getId());
    		return new ResponseEntity<>(updated, HttpStatus.CREATED);
    	} else {
    		return new ResponseEntity<>(new Nutrition(), HttpStatus.CONFLICT);
    	}
    }
    
    @DeleteMapping("deleteNutrition/{id}")
    public void deleteNutritionById(@PathVariable("id") long id) {
        service.deleteOne(id);
    }
}
