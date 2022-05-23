package com.chefApp.demo.model;

import com.chefApp.demo.model.builderdemo.Kippenpoot;
import com.chefApp.demo.model.builderdemo.Kippenpoot.KippenpootBuilder;

public class IngredientFactory {
	
	public static Ingredient getIngredient(String type) {
		if("sla".equals(type)) {
			// TODO Feitelijk moet sla nu een package private constructor hebben. Waarom???     (dus => Sla() {}  )
			Sla sla = new Sla();
			sla.setCost(2);
			return sla;
		} else {
			if("kippenpoot".equals(type)) {
				
				
				Kippenpoot result = new KippenpootBuilder("Tokkie")
						.metKosten(5)
						.metGewicht(30)
						.metVetgehalte(55)
						.build();
				
				return result;
				
			}
		}
		
		return null; // foei
	}

}
