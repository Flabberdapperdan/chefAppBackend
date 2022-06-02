package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.controller.NutrientService;
import com.chefApp.demo.model.Nutrient;

@RestController
@RequestMapping("api/nutrients")
public class NutrientEndpoint {
	@Autowired
	private NutrientService nutrientService;

	@GetMapping
	public List<Nutrient> getAll(){
		return nutrientService.readAll();
	}

	@GetMapping({"id"})
	public Nutrient getById(@PathVariable("id") long id) {
		return nutrientService.read(id).orElse(null);
	}
				
	@PostMapping
	public Nutrient create(@RequestBody Nutrient nutrient){
		//Validation
		Nutrient createdNutrient = nutrient;
		return nutrientService.create(createdNutrient);
	}
	
	@PutMapping("{id}")
	public Nutrient updateById(@PathVariable("id") long id, @RequestBody Nutrient nutrient){
		Optional<Nutrient> optionalNutrient = nutrientService.read(id);
		if(optionalNutrient.isPresent())
		{
			//Validation
			//Update properties
			Nutrient updatedNutrient = nutrient;
            updatedNutrient.setId(id);
			return nutrientService.update(updatedNutrient);
		}
		else
		{
			return null;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteById(@PathVariable("id")long id) {
		Optional<Nutrient> optionalNutrient = nutrientService.read(id);
		if(optionalNutrient.isPresent())
		{
			nutrientService.delete(id);
			return true;
		}
		else
		{
			return false;
		}
	}
}
