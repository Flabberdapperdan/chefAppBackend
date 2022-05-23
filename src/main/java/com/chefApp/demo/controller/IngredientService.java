package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utilities.ValidationException;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository r;

    public Optional<Ingredient> getOne(long id) {
        Optional<Ingredient> foundIngredient = r.findById(id);
        return foundIngredient;
    }

    public List<Ingredient> getAll() {
        return r.findAll();
    }

    public Ingredient createOne(Ingredient ingredient) {
        return r.save(ingredient);
    }

    public Object updateOne(Ingredient newIngredient, long id) {
        Ingredient oldIngredient = this.getOne(id).get();
        if (newIngredient.getName().length() > 0) {
            oldIngredient.setName(newIngredient.getName());
        } else {
            return new ValidationException();
        }
        if (newIngredient.getCost() > 0) {
            oldIngredient.setCost(newIngredient.getCost());
        } else {
            return new ValidationException();
        }
        Ingredient updatedIngredient = r.save(oldIngredient);
        return updatedIngredient;
    }
    public void deleteOne(long id) {
        r.deleteById(id);
    }

	public Optional<Ingredient> findByName(String name) {
		
		return this.r.findByName(name);
	}
	
	public List<Ingredient> findExpensive() {
		return this.r.dureIngredienten(5.0);
	}
}
