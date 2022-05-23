package com.chefApp.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.chefApp.demo.model.Nutrition;


@Component
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}

