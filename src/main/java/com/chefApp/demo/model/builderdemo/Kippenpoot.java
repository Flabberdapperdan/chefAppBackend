package com.chefApp.demo.model.builderdemo;

import com.chefApp.demo.model.VleesIngredient;

public class Kippenpoot extends VleesIngredient {

	private double vetGehalte;
	private double weight;
	
	private Kippenpoot(KippenpootBuilder builder) {
		this.setName(builder.name);
		this.setCost(builder.cost);
		this.vetGehalte = builder.vetGehalte;
		this.weight = builder.weight;
	}
	
	public double getVetGehalte() {
		return vetGehalte;
	}
	public void setVetGehalte(double vetGehalte) {
		this.vetGehalte = vetGehalte;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	// the builder provides a FLUENT API 
	public static class KippenpootBuilder {
		private String name;
		private double cost;
		private double vetGehalte;
		private double weight;
		
		// Geef hier alleen de verplichte fields mee
		public KippenpootBuilder(String name) {
			this.name = name;
		}
		
		public KippenpootBuilder metKosten(double cost) {
			this.cost = cost;
			return this;
		}
		
		public KippenpootBuilder metVetgehalte(double vetGehalte) {
			this.vetGehalte = vetGehalte;
			return this;
		}
		
		public KippenpootBuilder metGewicht(double weight) {
			this.weight = weight;
			return this;
		}
		
		public Kippenpoot build() {
			return new Kippenpoot(this);
		}
	}
}
