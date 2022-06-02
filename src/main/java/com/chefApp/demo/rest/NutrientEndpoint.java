package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.model.Nutrient;
import com.chefApp.demo.service.NutrientService;

@RestController
@RequestMapping("api/nutrients")
public class NutrientEndpoint {
	@Autowired
	private NutrientService nutrientService;

	Logger logger = Logger.getLogger(NutrientEndpoint.class.getName());

	@GetMapping
	public List<Nutrient> getAllNutrients(){
		return nutrientService.readAll();
	}

	@GetMapping({"id"})
	public Nutrient getNutrientById(@PathVariable("id") long id) {
		return nutrientService.read(id).orElse(null);
	}
				
	@PostMapping
	public Nutrient createNutrient(@RequestBody Nutrient nutrient){
		//Validation
		Nutrient createdNutrient = nutrient;
		return nutrientService.create(createdNutrient);
	}
	
	@PutMapping("{id}")
	public Nutrient updateNutrientById(@PathVariable("id") long id, @RequestBody Nutrient nutrient){
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
	public boolean deleteNutrientById(@PathVariable("id")long id) {
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
