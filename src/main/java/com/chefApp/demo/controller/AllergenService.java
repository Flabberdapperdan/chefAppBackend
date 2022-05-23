package com.chefApp.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Allergen;


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
	
	public void deleteOne(long id) {
		r.deleteById(id);
	}
}

