package com.chefApp.demo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.transform.Source;

import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.CreateIngredientAllergenRequest;
import com.chefApp.demo.dto.CreateIngredientNutrientRequest;
import com.chefApp.demo.dto.CreateIngredientRequest;
import com.chefApp.demo.dto.DeleteIngredientAllergenRequest;
import com.chefApp.demo.dto.DeleteIngredientNutrientRequest;
import com.chefApp.demo.dto.GetIngredientAllergenResponse;
import com.chefApp.demo.dto.GetIngredientNutrientResponse;
import com.chefApp.demo.dto.GetIngredientPageResponse;
import com.chefApp.demo.dto.GetIngredientResponse;
import com.chefApp.demo.dto.GetNutrientResponse;
import com.chefApp.demo.dto.UpdateIngredientAllergenRequest;
import com.chefApp.demo.dto.UpdateIngredientNutrientRequest;
import com.chefApp.demo.dto.UpdateIngredientRequest;
import com.chefApp.demo.model.Allergen;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.IngredientAllergen;
import com.chefApp.demo.model.IngredientNutrient;
import com.chefApp.demo.model.Nutrient;
import com.chefApp.demo.service.AllergenService;
import com.chefApp.demo.service.IngredientAllergenService;
import com.chefApp.demo.service.IngredientNutrientService;
import com.chefApp.demo.service.IngredientService;
import com.chefApp.demo.service.NutrientService;

@RestController
@RequestMapping("api/ingredients")
public class IngredientEndpoint {
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private IngredientNutrientService ingredientNutrientService;
	@Autowired
	private NutrientService nutrientService;
	@Autowired
	private IngredientAllergenService ingredientAllergenService;
	@Autowired
	private AllergenService allergenService;

	private ModelMapper modelMapper = new ModelMapper();
	Logger logger = Logger.getLogger(IngredientEndpoint.class.getName());

	///
	/// Ingredient
	///

	@GetMapping
	public GetIngredientPageResponse getAllIngredients(
		@RequestParam(name = "nutrients", required = false) boolean includeNutrients,
		@RequestParam(name = "allergens", required = false) boolean includeAllergens,
		@RequestParam(value="page", defaultValue = "0") int page,
		@RequestParam(value="size", defaultValue = "10") int size,
		@RequestParam(value="sort_by", defaultValue = "id") String sortBy,
		@RequestParam(value="order_by", defaultValue = "asc") String orderBy 
		){
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.valueOf(orderBy.toUpperCase()), sortBy));
		Page<Ingredient> ingredientsPage = ingredientService.getAll(pageable);
		GetIngredientPageResponse ingredientPageResponse = new GetIngredientPageResponse();
		ingredientPageResponse.setCurrentPage(ingredientsPage.getNumber());
		ingredientPageResponse.setTotalPages(ingredientsPage.getTotalPages());
		ingredientPageResponse.setTotalItems(ingredientsPage.getTotalElements());
		ingredientPageResponse.setIngredients(ingredientsPage.stream().map(ingredient -> {
			return constructIngredientResponse(ingredient, includeNutrients, includeAllergens);
    	}).collect(Collectors.toList()));
		return ingredientPageResponse;
	}

	@GetMapping("{id}")
	public GetIngredientResponse getIngredientById(@PathVariable("id") long id, @RequestParam(name = "nutrients", required = false) boolean includeNutrients, @RequestParam(name = "allergens", required = false) boolean includeAllergens) {
		Optional<Ingredient> optionalIngredient = ingredientService.read(id);
		if(optionalIngredient.isPresent())
		{
			return constructIngredientResponse(optionalIngredient.get(), includeNutrients, includeAllergens);
		}
		else
		{
			return null;
		}
	}
				
	@PostMapping
	public GetIngredientResponse createIngredient(@RequestBody CreateIngredientRequest ingredientRequest){
		//Validation
		Ingredient ingredient = modelMapper.map(ingredientRequest, Ingredient.class);
		return modelMapper.map(ingredientService.create(ingredient), GetIngredientResponse.class);
	}
	
	@PutMapping("{id}")
	public GetIngredientResponse updateIngredientById(@PathVariable("id") long id, @RequestBody UpdateIngredientRequest ingredientRequest){
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		Optional<Ingredient> optionalIngredient = ingredientService.read(id);
		if(optionalIngredient.isPresent())
		{
			//Validation
			//Update properties
			Ingredient ingredient = optionalIngredient.get();
			modelMapper.map(ingredientRequest, ingredient);
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return modelMapper.map(ingredientService.update(ingredient), GetIngredientResponse.class);
		}
		else
		{
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return null;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteIngredientById(@PathVariable("id")long id) {
		Optional<Ingredient> optionalIngredient = ingredientService.read(id);
		if(optionalIngredient.isPresent())
		{
			ingredientService.delete(id);
			return true;
		}
		else
		{
			return false;
		}
	}

    ///
    /// IngredientNutrient
    ///

    @GetMapping({"{ingredientId}/nutrients"})
	public List<GetIngredientNutrientResponse> getIngredientNutrients(@PathVariable("ingredientId") long ingredientId){
		List<IngredientNutrient> ingredientNutrients = ingredientNutrientService.findByIngredientId(ingredientId);
		return ingredientNutrients.stream().map(ingredientNutrient -> {
			return modelMapper.map(ingredientNutrient, GetIngredientNutrientResponse.class);
		}).collect(Collectors.toList());
	}
				
	@PostMapping("{ingredientId}/nutrients/{nutrientId}")
	public GetIngredientNutrientResponse createIngredientNutrient(@PathVariable("ingredientId") long ingredientId, @PathVariable("nutrientId") long nutrientId, @RequestBody CreateIngredientNutrientRequest ingredientNutrientRequest){
		Optional<Ingredient> optionalIngredient = ingredientService.read(ingredientId);
		Optional<Nutrient> optionalNutrient = nutrientService.read(nutrientId);
		if(optionalIngredient.isPresent() && optionalNutrient.isPresent())
		{
			IngredientNutrient ingredientNutrient = new IngredientNutrient();
			ingredientNutrient.setIngredient(optionalIngredient.get());
			ingredientNutrient.setNutrient(optionalNutrient.get());
			ingredientNutrient.setQuantity(ingredientNutrientRequest.getQuantity());
			return modelMapper.map(ingredientNutrientService.create(ingredientNutrient), GetIngredientNutrientResponse.class);
		}
		else
		{
			return null;
		}
	}
	
	@PutMapping("{ingredientId}/nutrients/{nutrientId}")
	public GetIngredientNutrientResponse updateIngredientNutrient(@PathVariable("ingredientId") long ingredientId, @PathVariable("nutrientId") long nutrientId, @RequestBody UpdateIngredientNutrientRequest ingredientNutrientRequest){
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		Optional<Ingredient> optionalIngredient = ingredientService.read(ingredientId);
		Optional<Nutrient> optionalNutrient = nutrientService.read(nutrientId);
		Optional<IngredientNutrient> optionalIngredientNutrient = ingredientNutrientService.read(ingredientNutrientRequest.getId());
		if(optionalIngredient.isPresent() && optionalNutrient.isPresent() && optionalIngredientNutrient.isPresent())
		{
			IngredientNutrient ingredientNutrient = optionalIngredientNutrient.get();
			ingredientNutrient.setIngredient(optionalIngredient.get());
			ingredientNutrient.setNutrient(optionalNutrient.get());
			ingredientNutrient.setQuantity(ingredientNutrientRequest.getQuantity());
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return modelMapper.map(ingredientNutrientService.update(ingredientNutrient), GetIngredientNutrientResponse.class);
		}
		else
		{
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return null;
		}
	}
	
	@DeleteMapping("{ingredientId}/nutrients/{nutrientId}")
	public boolean deleteIngredientNutrient(@PathVariable("ingredientId") long ingredientId, @PathVariable("nutrientId") long nutrientId, @RequestBody DeleteIngredientNutrientRequest ingredientNutrientRequest) {
		Optional<IngredientNutrient> optionalIngredientNutrient = ingredientNutrientService.read(ingredientNutrientRequest.getId());
		if(optionalIngredientNutrient.isPresent())
		{
			IngredientNutrient ingredientNutrient = optionalIngredientNutrient.get();
			if(ingredientNutrient.getIngredient().getId() == ingredientId && ingredientNutrient.getNutrient().getId() == nutrientId)
			{
				ingredientNutrientService.delete(ingredientNutrientRequest.getId());
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

    ///
    /// IngredientAllergen
    ///

    @GetMapping({"{ingredientId}/allergens"})
	public List<GetIngredientAllergenResponse> getIngredientAllergens(@PathVariable("ingredientId") long ingredientId){
		List<IngredientAllergen> ingredientAllergens = ingredientAllergenService.findByIngredientId(ingredientId);
		return ingredientAllergens.stream().map(ingredientAllergen -> {
			return modelMapper.map(ingredientAllergen, GetIngredientAllergenResponse.class);
		}).collect(Collectors.toList());
	}
				
	@PostMapping("{ingredientId}/allergens/{allergenId}")
	public GetIngredientAllergenResponse createIngredientAllergen(@PathVariable("ingredientId") long ingredientId, @PathVariable("allergenId") long allergenId, @RequestBody CreateIngredientAllergenRequest ingredientAllergenRequest){
		Optional<Ingredient> optionalIngredient = ingredientService.read(ingredientId);
		Optional<Allergen> optionalAllergen = allergenService.read(allergenId);
		if(optionalIngredient.isPresent() && optionalAllergen.isPresent())
		{
			IngredientAllergen ingredientAllergen = new IngredientAllergen();
			ingredientAllergen.setIngredient(optionalIngredient.get());
			ingredientAllergen.setAllergen(optionalAllergen.get());
			return modelMapper.map(ingredientAllergenService.create(ingredientAllergen), GetIngredientAllergenResponse.class);
		}
		else
		{
			return null;
		}
	}
	
	@PutMapping("{ingredientId}/allergens/{allergenId}")
	public GetIngredientAllergenResponse updateIngredientAllergen(@PathVariable("ingredientId") long ingredientId, @PathVariable("allergenId") long allergenId, @RequestBody UpdateIngredientAllergenRequest ingredientAllergenRequest){
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		Optional<Ingredient> optionalIngredient = ingredientService.read(ingredientId);
		Optional<Allergen> optionalAllergen = allergenService.read(allergenId);
		Optional<IngredientAllergen> optionalIngredientAllergen = ingredientAllergenService.read(ingredientAllergenRequest.getId());
		if(optionalIngredient.isPresent() && optionalAllergen.isPresent() && optionalIngredientAllergen.isPresent())
		{
			IngredientAllergen ingredientAllergen = optionalIngredientAllergen.get();
			ingredientAllergen.setIngredient(optionalIngredient.get());
			ingredientAllergen.setAllergen(optionalAllergen.get());
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return modelMapper.map(ingredientAllergenService.update(ingredientAllergen), GetIngredientAllergenResponse.class);
		}
		else
		{
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return null;
		}
	}
	
	@DeleteMapping("{ingredientId}/allergens/{allergenId}")
	public boolean deleteIngredientAllergen(@PathVariable("ingredientId") long ingredientId, @PathVariable("allergenId") long allergenId, @RequestBody DeleteIngredientAllergenRequest ingredientAllergenRequest) {
		Optional<IngredientAllergen> optionalIngredientAllergen = ingredientAllergenService.read(ingredientAllergenRequest.getId());
		if(optionalIngredientAllergen.isPresent())
		{
			IngredientAllergen ingredientAllergen = optionalIngredientAllergen.get();
			if(ingredientAllergen.getIngredient().getId() == ingredientId && ingredientAllergen.getAllergen().getId() == allergenId)
			{
				ingredientAllergenService.delete(ingredientAllergenRequest.getId());
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	///
	/// Dto constructors
	///
	private GetIngredientResponse constructIngredientResponse(Ingredient ingredient, boolean includeNutrients, boolean includeAllergens)
	{
		GetIngredientResponse ingredientResponse = modelMapper.map(ingredient, GetIngredientResponse.class);
		if(includeNutrients)
		{
			List<IngredientNutrient> ingredientNutrients = ingredientNutrientService.findByIngredientId(ingredient.getId());
			List<GetIngredientNutrientResponse> ingredientNutrientResponses = ingredientNutrients.stream().map(ingredientNutrient -> {
				return modelMapper.map(ingredientNutrient, GetIngredientNutrientResponse.class);
			}).collect(Collectors.toList());
			ingredientResponse.setNutrients(ingredientNutrientResponses);
		}
		if(includeAllergens)
		{
			List<IngredientAllergen> ingredientAllergens = ingredientAllergenService.findByIngredientId(ingredient.getId());
			List<GetIngredientAllergenResponse> ingredientAllergenResponses = ingredientAllergens.stream().map(ingredientAllergen -> {
				return modelMapper.map(ingredientAllergen, GetIngredientAllergenResponse.class);
			}).collect(Collectors.toList());
			ingredientResponse.setAllergens(ingredientAllergenResponses);
		}
		return ingredientResponse;
	}
}
