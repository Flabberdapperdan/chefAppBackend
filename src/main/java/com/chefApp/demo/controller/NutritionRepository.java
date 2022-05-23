package com.chefApp.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chefApp.demo.model.Nutrition;


@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}

