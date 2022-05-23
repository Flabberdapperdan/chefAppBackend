package com.chefApp.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Allergen;


@Service
public class AllergenService {
	@Autowired
	AllergenRepository r;

	public Optional<Allergen> getOne(long id){
		Optional<Allergen> foundAllergen = r.findById(id);
	return foundAllergen;
	}
	public void CreateOne(Allergen allergen) {
		r.save(allergen);
	}
	public void deleteOne(long id) {
		r.deleteById(id);
	}
}

