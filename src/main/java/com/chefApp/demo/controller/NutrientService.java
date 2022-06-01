package com.chefApp.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefApp.demo.model.Nutrient;

import utilities.ValidationException;

@Service
public class NutrientService {
	@Autowired
	NutrientRepository r;
	
	public Optional<Nutrient> getOne(Long id) {
		Optional<Nutrient> foundNutrition = r.findById(id);
		return foundNutrition;		
	}
	
	public List<Nutrient> getAll() {
		return r.findAll();
	}
	
	/*
	 * What is a Transaction
	 * Een transactie is feitelijk dat deze method alles of niets doet.
	 * Dus of: hij gaat helemaal goed
	 * Of: er gaat iets mis (bijvoorbeeld in de database) en dan worden alle veranderingen teruggedraaid.
	 * 
	 * Let wel: dit kan alleen op public methods en de TRANSACTIE WORDT DAN EN SLECHTS DAN GESTART ALS HIJ VANUIT EEN andere class wordt aangeroepen.
	 * (Dit schrijf ik met hoofdletters, dit keer bewust, want als je vanuit de method hierboven getAll de createOne aanroept gaat het vout en
	 * wordt er geen transactie gestart). Dat is zelfs bij senior developers een vaak voorkomend misverstand.
	 */
	@Transactional // creates a Transaction
	public Nutrient createOne(Nutrient nutrition) {
	return r.save(nutrition);
	}
	
	 public Object updateOne(Nutrient newNutrition, long id) {
		Nutrient oldNutrition = this.getOne(id).get();
	        if (newNutrition.getName().length() > 0) {
	            oldNutrition.setName(newNutrition.getName());
	        } else {
	            return new ValidationException();
	        }
	       
	        
	        Nutrient updatedNutrition= r.save(oldNutrition);
	        return updatedNutrition;
	 }
	public void deleteOne(long id) {
	r.deleteById(id);
	}
}
