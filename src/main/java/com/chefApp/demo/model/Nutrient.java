package com.chefApp.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Nutrient {
	// primary key    AutoIncrement
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
	private String code;
	@NotBlank(message = "Name is required.")
    private String name;
	@Column(name = "grp")
	@NotBlank(message = "Group is required.")
	private String group;
	@NotBlank(message = "Unit is required.")
	private String unit;

	//link to IngredientNutrient
	@OneToMany(mappedBy = "nutrient", cascade = CascadeType.REMOVE)
	List<IngredientNutrient> ingredientNutrients;
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
