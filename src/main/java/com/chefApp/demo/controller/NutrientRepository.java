package com.chefApp.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chefApp.demo.model.Nutrient;


@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
}

