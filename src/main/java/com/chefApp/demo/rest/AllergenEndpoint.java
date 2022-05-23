package com.chefApp.demo.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chefApp.demo.controller.AllergenService;
import com.chefApp.demo.model.Allergen;

@RestController
public class AllergenEndpoint {
	@Autowired
	AllergenService service;
	@AutoMapping("api/allergen")
	@GetMapping({"id"})
	public Allergen getAllergenById(@PathVariable() long id) {
		Allergen foundAllergen = service.getOne(id).get();
		return foundAllergen;		
	}
	@PostMapping
	public List<Allergen> createNewAllergen(@RequestBody Allergen allergen){
		service.CreateOne(allergen);
		return Arrays.asList(allergen);
	@DeleteMapping("{id}")
	public Allergen getAllergenById(@PathVariable() long id) {
		
		
	}
	}
}
