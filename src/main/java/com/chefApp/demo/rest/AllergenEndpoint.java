package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;

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
		return new ResponseEntity<>(this.service.CreateOne(allergen), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Allergen> updateById(@PathVariable() long id, @RequestBody Allergen input){
		Allergen newAllergen = input;
		Optional<Allergen> oldAllergen = this.service.getOne(input.getId());
		if(oldAllergen.isEmpty()==false) {
			Allergen updated = (Allergen) this.service.updateOne(newAllergen, oldAllergen.get().getId());
			return new ResponseEntity<>(updated,HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new Allergen(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deleteAllergenById(@PathVariable()long id) {
		if (id >= 0) {
			Optional<Allergen> exists = service.getOne(id);
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
	

