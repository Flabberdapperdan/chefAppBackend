package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.controller.NutrientService;
import com.chefApp.demo.model.Nutrient;

@RestController
@RequestMapping("api/nutrition")
public class NutrientEndpoint {
    @Autowired
    NutrientService service;

    @GetMapping("{id}")
    public Nutrient getNutritionById(@PathVariable() long id) {
        Nutrient foundNutrition = service.getOne(id).get();
        return foundNutrition;
    }

    @GetMapping
    public List<Nutrient> getAllNutrition() {
        List<Nutrient> allNutrition = service.getAll();
        return allNutrition;
    }

    @PostMapping
    public ResponseEntity<Nutrient> createNewNutrition(@RequestBody Nutrient nutrition) {
    	return new ResponseEntity<Nutrient>(this.service.createOne(nutrition), HttpStatus.CREATED);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Nutrient> updateById(@PathVariable() long id, @RequestBody Nutrient input){
    	Nutrient newNutrition = input;
    	Optional<Nutrient> oldNutrition = this.service.getOne(input.getId());
    	if (oldNutrition.isEmpty()==false) {
    		Nutrient updated = (Nutrient) this.service.updateOne(newNutrition, oldNutrition.get().getId());
    		return new ResponseEntity<>(updated, HttpStatus.CREATED);
    	} else {
    		return new ResponseEntity<>(new Nutrient(), HttpStatus.CONFLICT);
    	}
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity deleteNutritionById(@PathVariable("id")long id) {
        if (id >= 0) {
        	Optional<Nutrient> exists = service.getOne(id);
        	if(exists.isPresent()) {
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
