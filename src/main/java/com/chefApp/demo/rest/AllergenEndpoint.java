package com.chefApp.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.controller.AllergenService;
import com.chefApp.demo.model.Allergen;


@RestController
@RequestMapping("api/allergen")
public class AllergenEndpoint {
	
	@Autowired
	private AllergenService service;
	
	@GetMapping({"id"})
	public Allergen getAllergenById(@PathVariable() long id) {
		Allergen foundAllergen = service.getOne(id).get();
		return foundAllergen;		
	}
	
	@GetMapping
	public List<Allergen> getAllAllergens(){
		List<Allergen> allAllergens = service.getAll();
		return allAllergens;
	}
				
	@PostMapping
	public ResponseEntity<Allergen> createNewAllergen(@RequestBody Allergen allergen){
		
		Allergen saved = this.service.CreateOne(allergen);
		
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public Allergen updateById(@PathVariable long id, @RequestBody Allergen input) {	
		return null;
	}
	
	
	@DeleteMapping("{id}")
	public void  deleteAllergenById(@PathVariable() long id) {
		service.deleteOne(id);
		
	}
	
}
