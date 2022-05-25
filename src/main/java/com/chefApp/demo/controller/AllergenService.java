package com.chefApp.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Allergen;
import com.chefApp.demo.model.Nutrition;

import utilities.ValidationException;


@Service
public class AllergenService {
	@Autowired
	private AllergenRepository r;

	public Optional<Allergen> getOne(long id){
		Optional<Allergen> foundAllergen = r.findById(id);
		return foundAllergen;
	}
	
	public List<Allergen> getAll() {
		return r.findAll();
	}
	
	public Allergen CreateOne(Allergen allergen) {
		return r.save(allergen);
	}
	 public Object updateOne(Allergen newAllergen, long id) {
	        Allergen oldAllergen = this.getOne(id).get();
	        if (newAllergen.getName().length() > 0) {
	            oldAllergen.setName(newAllergen.getName());
	        } else {
	            return new ValidationException();
	        }
	       
	        
	        Allergen updatedAllergen= r.save(oldAllergen);
	        return updatedAllergen;
	 }
	
	public void deleteOne(long id) {
		r.deleteById(id);
	}
}

