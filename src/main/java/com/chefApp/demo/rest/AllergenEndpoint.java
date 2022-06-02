package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.controller.AllergenService;
import com.chefApp.demo.model.Allergen;

@RestController
@RequestMapping("api/allergens")
public class AllergenEndpoint {
	@Autowired
	private AllergenService allergenService;

	@GetMapping
	public List<Allergen> getAll(){
		return allergenService.readAll();
	}

	@GetMapping({"{id}"})
	public Allergen getById(@PathVariable("id") long id) {
		return allergenService.read(id).orElse(null);
	}
				
	@PostMapping
	public Allergen create(@RequestBody Allergen allergen){
		//Validation
		Allergen createdAllergen = allergen;
		return allergenService.create(createdAllergen);
	}
	
	@PutMapping("{id}")
	public Allergen updateById(@PathVariable("id") long id, @RequestBody Allergen allergen){
		Optional<Allergen> optionalAllergen = allergenService.read(id);
		if(optionalAllergen.isPresent())
		{
			//Validation
			//Update properties
			Allergen updatedAllergen = allergen;
			updatedAllergen.setId(id);
			return allergenService.update(updatedAllergen);
		}
		else
		{
			return null;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteById(@PathVariable("id")long id) {
		Optional<Allergen> optionalAllergen = allergenService.read(id);
		if(optionalAllergen.isPresent())
		{
			allergenService.delete(id);
			return true;
		}
		else
		{
			return false;
		}
	}
}
	

