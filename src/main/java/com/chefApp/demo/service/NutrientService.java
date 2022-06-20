package com.chefApp.demo.service;

import com.chefApp.demo.model.Nutrient;
import com.chefApp.demo.repository.NutrientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import java.util.Optional;

@Service
public class NutrientService {
    @Autowired
    private NutrientRepository nutrientRepository;

    Logger logger = Logger.getLogger(NutrientService.class.getName());

    public Page<Nutrient> getAll(Pageable pageable) {
        return nutrientRepository.findAll(pageable);
    }

    public Optional<Nutrient> read(long id) {
       return nutrientRepository.findById(id);
    }

    @Transactional
    public Nutrient create(Nutrient nutrient) {
		//Data Access Verification
        return nutrientRepository.save(nutrient);
    }

    @Transactional
    public Nutrient update(Nutrient nutrient) {
		//Data Access Verification
        return nutrientRepository.save(nutrient);
    }
    
    @Transactional
    public void delete(long id) {
        nutrientRepository.deleteById(id);
    }
}
