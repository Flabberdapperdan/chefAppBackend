package com.chefApp.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {

		@Id
        @GeneratedValue ( strategy = GenerationType.AUTO )
        long id;
		int userId;
		String name;
		double cost;
		double salePrice;

		//getters and setters\\
		public int getUserId() {
		return userId;
		}
		public void setUserId(int userId) {
		this.userId = userId;
		}
		public double getCost() {
		return cost;
		}
		public void setCost(double cost) {
		this.cost = cost;
		}
		public double getSalePrice() {
		return salePrice;
		}
		public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
