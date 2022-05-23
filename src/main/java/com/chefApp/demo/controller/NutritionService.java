package com.chefApp.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.chefApp.demo.model.Nutrition;


public class NutritionService {
	@Autowired
	NutritionRepository r;
	
	public Optional<Nutrition> getOne(Long id) {
		Optional<Nutrition> foundNutrition = r.findById(id);
		return foundNutrition;		
	}
	
	public List<Nutrition> getAll() {
		return r.findAll();
	}
	public void createOne(Nutrition nutrition) {
	r.save(nutrition);
	}
	public void deleteOne(long id) {
	r.deleteById(id);
	}
}
