package com.chefApp.demo.controller;

import com.chefApp.demo.model.Nutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.Optional;

@Service
public class NutrientService {
    @Autowired
    private NutrientRepository nutrientRepository;

    Logger logger = Logger.getLogger(NutrientService.class.getName());

    public List<Nutrient> readAll() {
        return nutrientRepository.findAll();
    }

    public Optional<Nutrient> read(long id) {
       return nutrientRepository.findById(id);
    }

    public Nutrient create(Nutrient nutrient) {
		//Data Access Verification
        return nutrientRepository.save(nutrient);
    }

    public Nutrient update(Nutrient nutrient) {
		//Data Access Verification
        return nutrientRepository.save(nutrient);
    }
    public void delete(long id) {
        nutrientRepository.deleteById(id);
    }
}
