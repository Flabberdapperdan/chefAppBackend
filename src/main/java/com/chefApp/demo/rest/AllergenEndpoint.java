package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.model.Allergen;
import com.chefApp.demo.service.AllergenService;

@RestController
@RequestMapping("api/allergens")
public class AllergenEndpoint {
	@Autowired
	private AllergenService allergenService;

	Logger logger = Logger.getLogger(AllergenEndpoint.class.getName());

	@GetMapping
	public List<Allergen> getAllAllergens(){
		return allergenService.readAll();
	}

	@GetMapping({"{id}"})
	public Allergen getAllergenById(@PathVariable("id") long id) {
		return allergenService.read(id).orElse(null);
	}
				
	@PostMapping
	public Allergen createAllergen(@RequestBody Allergen allergen){
		//Validation
		Allergen createdAllergen = allergen;
		return allergenService.create(createdAllergen);
	}
	
	@PutMapping("{id}")
	public Allergen updateAllergenById(@PathVariable("id") long id, @RequestBody Allergen allergen){
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
	public Allergen deleteAllergenById(@PathVariable("id")long id) {
		Optional<Allergen> optionalAllergen = allergenService.read(id);
		if(optionalAllergen.isPresent())
		{
			allergenService.delete(id);
		}
		return optionalAllergen.orElse(null);
	}
}
	

