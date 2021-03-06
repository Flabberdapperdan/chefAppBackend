package com.chefApp.demo.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Allergen;
import com.chefApp.demo.repository.AllergenRepository;

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
	
	@Transactional
	public Allergen create(Allergen allergen) {
		//Data Access Verification
		return allergenRepository.save(allergen);
	}
	
	@Transactional
	public Allergen update(Allergen allergen) {
		//Data Access Verification
		return allergenRepository.save(allergen);
	}
	
	@Transactional
	public void delete(long id) {
		allergenRepository.deleteById(id);
	}
}

