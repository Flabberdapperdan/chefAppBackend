package com.chefApp.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Nutrition;

import utilities.ValidationException;

@Service
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
	public Nutrition createOne(Nutrition nutrition) {
	return r.save(nutrition);
	}
	
	 public Object updateOne(Nutrition newNutrition, long id) {
	        Nutrition oldNutrition = this.getOne(id).get();
	        if (newNutrition.getName().length() > 0) {
	            oldNutrition.setName(newNutrition.getName());
	        } else {
	            return new ValidationException();
	        }
	       
	        
	        Nutrition updatedNutrition= r.save(oldNutrition);
	        return updatedNutrition;
	 }
	public void deleteOne(long id) {
	r.deleteById(id);
	}
}
