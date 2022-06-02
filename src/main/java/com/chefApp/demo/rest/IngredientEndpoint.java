package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException.NotImplemented;

import com.chefApp.demo.controller.IngredientService;
import com.chefApp.demo.model.Ingredient;

@RestController
@RequestMapping("api/ingredients")
public class IngredientEndpoint {
	@Autowired
	private IngredientService ingredientService;

	@GetMapping
	public List<Ingredient> getAll(){
		return ingredientService.readAll();
	}

	@GetMapping({"id"})
	public Ingredient getById(@PathVariable("id") long id) {
		return ingredientService.read(id).orElse(null);
	}
				
	@PostMapping
	public Ingredient create(@RequestBody Ingredient ingredient){
		//Validation
		Ingredient createdIngredient = ingredient;
		return ingredientService.create(createdIngredient);
	}
	
	@PutMapping("{id}")
	public Ingredient updateById(@PathVariable("id") long id, @RequestBody Ingredient ingredient){
		Optional<Ingredient> optionalIngredient = ingredientService.read(id);
		if(optionalIngredient.isPresent())
		{
			//Validation
			//Update properties
			Ingredient updatedIngredient = ingredient;
            updatedIngredient.setId(id);
			return ingredientService.update(updatedIngredient);
		}
		else
		{
			return null;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteById(@PathVariable("id")long id) {
		Optional<Ingredient> optionalIngredient = ingredientService.read(id);
		if(optionalIngredient.isPresent())
		{
			ingredientService.delete(id);
			return true;
		}
		else
		{
			return false;
		}
	}

    ///
    /// IngredientNutrient
    ///

    @GetMapping({"{id}/nutrients"})
	public HttpStatus getIngredientNutrients(){
        return HttpStatus.NOT_IMPLEMENTED;
	}
				
	@PostMapping("{id}/nutrients")
	public HttpStatus createIngredientNutrient(@RequestBody Ingredient ingredient){
        return HttpStatus.NOT_IMPLEMENTED;
	}
	
	@PutMapping("{id}/nutrients")
	public HttpStatus updateIngredientNutrient(@PathVariable("id") long id, @RequestBody Ingredient ingredient){
        return HttpStatus.NOT_IMPLEMENTED;
	}
	
	@DeleteMapping("{id}/nutrients")
	public HttpStatus deleteIngredientNutrient(@PathVariable("id")long id) {
        return HttpStatus.NOT_IMPLEMENTED;
	}

    ///
    /// IngredientAllergen
    ///

    @GetMapping({"{id}/allergens"})
	public HttpStatus getIngredientAllergens(){
        return HttpStatus.NOT_IMPLEMENTED;
	}
				
	@PostMapping("{id}/allergens")
	public HttpStatus createIngredientAllergen(@RequestBody Ingredient ingredient){
        return HttpStatus.NOT_IMPLEMENTED;
	}
	
	@PutMapping("{id}/allergens")
	public HttpStatus updateIngredientAllergen(@PathVariable("id") long id, @RequestBody Ingredient ingredient){
        return HttpStatus.NOT_IMPLEMENTED;
	}
	
	@DeleteMapping("{id}/allergens")
	public HttpStatus deleteIngredientAllergen(@PathVariable("id")long id) {
        return HttpStatus.NOT_IMPLEMENTED;
	}
}
