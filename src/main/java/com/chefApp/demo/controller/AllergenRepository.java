package com.chefApp.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chefApp.demo.model.Allergen;

@Repository // subtype of @Component
public interface AllergenRepository extends JpaRepository<Allergen, Long>{}