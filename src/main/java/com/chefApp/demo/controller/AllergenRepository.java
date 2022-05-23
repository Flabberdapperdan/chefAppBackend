package com.chefApp.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.chefApp.demo.model.Allergen;

@Component
public interface AllergenRepository extends JpaRepository<Allergen, Long>{}