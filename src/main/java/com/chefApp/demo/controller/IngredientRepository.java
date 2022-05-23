package com.chefApp.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.chefApp.demo.model.Ingredient;

@Component
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	// 2e versnelling
	Optional<Ingredient> findByName(String name);

	List<Ingredient> findByCostLessThanOrderByName(double kosten);

	// 3e versnelling
	// met JPQL (Java Persistence Query Language)
	@Query("select i from Ingredient i where i.cost > :kosten")
	List<Ingredient> dureIngredienten(double kosten);

// 3e  versnelling
	// met Native Query
	@Query(value = "select * from ingredient where cost > :kosten", nativeQuery = true)
	List<Ingredient> dureIngredientenNative(double kosten);
}
