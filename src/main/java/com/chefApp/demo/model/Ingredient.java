package com.chefApp.demo.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Entity
public class Ingredient {

    // primary key    AutoIncrement
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Min(value = 0, message= "Code must be a positive number.")
    private long code;
    @NotBlank(message = "Name is required.")
    private String name;
    @Column(name = "grp")
    @NotBlank(message = "Group is required.")
    private String group;
    @Column(name = "market_price")
    @DecimalMin(value = "0.0", message= "Market Price must be a positive number.")
    private BigDecimal marketprice;
    
    //link to RecipeIngredient
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<RecipeIngredient> recipeIngredients;

    //link to IngredientNutrient
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<IngredientNutrient> ingredientNutrients;

    //link to IngredientAllergen
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<IngredientAllergen> ingredientAllergens;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getCode() {
        return code;
    }
    public void setCode(long code) {
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

    public BigDecimal getMarketprice() {
        return marketprice;
    }
    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }
    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public List<IngredientNutrient> getIngredientNutrients() {
        return ingredientNutrients;
    }
    public void setIngredientNutrients(List<IngredientNutrient> ingredientNutrients) {
        this.ingredientNutrients = ingredientNutrients;
    }

    public List<IngredientAllergen> getIngredientAllergens() {
        return ingredientAllergens;
    }
    public void setIngredientAllergens(List<IngredientAllergen> ingredientAllergens) {
        this.ingredientAllergens = ingredientAllergens;
    }
}
