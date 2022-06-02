package com.chefApp.demo.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Allergen;

@Service
public class AllergenService {
	@Autowired
	private AllergenRepository allergenRepository;

	Logger logger = Logger.getLogger(AllergenService.class.getName());
	
	public List<Allergen> readAll() {
		return allergenRepository.findAll();
	}

	public Optional<Allergen> read(long id){
		return allergenRepository.findById(id);
	}
	
	public Allergen create(Allergen allergen) {
		//Data Access Verification
		return allergenRepository.save(allergen);
	}
	
	public Allergen update(Allergen allergen) {
		//Data Access Verification
		return allergenRepository.save(allergen);
	}
	
	public void delete(long id) {
		allergenRepository.deleteById(id);
	}
}

