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

import com.chefApp.demo.dto.CreateIngredientNutrientRequest;
import com.chefApp.demo.dto.CreateIngredientRequest;
import com.chefApp.demo.dto.DeleteIngredientNutrientRequest;
import com.chefApp.demo.dto.GetIngredientAllergenResponse;
import com.chefApp.demo.dto.GetIngredientNutrientResponse;
import com.chefApp.demo.dto.GetIngredientPageResponse;
import com.chefApp.demo.dto.GetIngredientResponse;
import com.chefApp.demo.dto.UpdateIngredientNutrientRequest;
import com.chefApp.demo.dto.UpdateIngredientRequest;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.IngredientAllergen;
import com.chefApp.demo.model.IngredientNutrient;
import com.chefApp.demo.model.Nutrient;
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
		// add a modelmapper wrapper soon
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

    @GetMapping({"{id}/nutrients"})
	public List<GetIngredientNutrientResponse> getIngredientNutrients(@PathVariable("id")long id){
		List<IngredientNutrient> ingredientNutrients = ingredientNutrientService.findByIngredientId(id);
		return ingredientNutrients.stream().map(ingredientNutrient -> {
			GetIngredientNutrientResponse ingredientNutrientResponseDto = modelMapper.map(ingredientNutrient.getNutrient(), GetIngredientNutrientResponse.class);
			ingredientNutrientResponseDto.setJoinId(ingredientNutrient.getId());
			ingredientNutrientResponseDto.setQuantity(ingredientNutrient.getQuantity());
			return ingredientNutrientResponseDto;
		}).collect(Collectors.toList());
	}
				
	@PostMapping("{id}/nutrients")
	public GetIngredientNutrientResponse createIngredientNutrient(@PathVariable("id")long id, @RequestBody CreateIngredientNutrientRequest ingredientNutrientRequest){
		Optional<Ingredient> optionalIngredient = ingredientService.read(id);
		Optional<Nutrient> optionalNutrient = nutrientService.read(ingredientNutrientRequest.getNutrientId());
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
	
	@PutMapping("{id}/nutrients")
	public GetIngredientNutrientResponse updateIngredientNutrient(@PathVariable("id") long id, @RequestBody UpdateIngredientNutrientRequest ingredientNutrientRequest){
		Optional<Ingredient> optionalIngredient = ingredientService.read(id);
		Optional<Nutrient> optionalNutrient = nutrientService.read(ingredientNutrientRequest.getNutrientId());
		Optional<IngredientNutrient> optionalIngredientNutrient = ingredientNutrientService.read(ingredientNutrientRequest.getJoinId());
		if(optionalIngredient.isPresent() && optionalNutrient.isPresent() && optionalIngredientNutrient.isPresent())
		{
			IngredientNutrient ingredientNutrient = optionalIngredientNutrient.get();
			ingredientNutrient.setIngredient(optionalIngredient.get());
			ingredientNutrient.setNutrient(optionalNutrient.get());
			ingredientNutrient.setQuantity(ingredientNutrientRequest.getQuantity());
			return modelMapper.map(ingredientNutrientService.update(ingredientNutrient), GetIngredientNutrientResponse.class);
		}
		else
		{
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return null;
		}
	}
	
	@DeleteMapping("{id}/nutrients")
	public boolean deleteIngredientNutrient(@PathVariable("id")long id, @RequestBody DeleteIngredientNutrientRequest ingredientNutrientRequest) {
		Optional<IngredientNutrient> optionalIngredientNutrient = ingredientNutrientService.read(ingredientNutrientRequest.getJoinId());
		if(optionalIngredientNutrient.isPresent())
		{
			ingredientNutrientService.delete(optionalIngredientNutrient.get().getId());
			return true;
		}
		else
		{
			return false;
		}
	}

    ///
    /// IngredientAllergen
    ///

    @GetMapping({"{id}/allergens"})
	public List<GetIngredientAllergenResponse> getIngredientAllergens(@PathVariable("id")long id){
		List<IngredientAllergen> ingredientAllergens = ingredientAllergenService.findByIngredientId(id);
		return ingredientAllergens.stream().map(ingredientAllergen -> {
			GetIngredientAllergenResponse ingredientAllergenResponseDto = modelMapper.map(ingredientAllergen.getAllergen(), GetIngredientAllergenResponse.class);
			return ingredientAllergenResponseDto;
		}).collect(Collectors.toList());
	}
				
	@PostMapping("{id}/allergens")
	public HttpStatus createIngredientAllergen(@PathVariable("id")long id, @RequestBody Ingredient ingredient){
        return HttpStatus.NOT_IMPLEMENTED;
	}
	
	@PutMapping("{id}/allergens")
	public HttpStatus updateIngredientAllergen(@PathVariable("id") long id, @RequestBody Ingredient ingredient){
        return HttpStatus.NOT_IMPLEMENTED;
	}
	
	@DeleteMapping("{id}/allergens")
	public HttpStatus deleteIngredientAllergen(@PathVariable("id")long id) {
        return HttpStatus.NOT_IMPLEMENTED;
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
				GetIngredientNutrientResponse ingredientNutrientResponse = modelMapper.map(ingredientNutrient.getNutrient(), GetIngredientNutrientResponse.class);
				ingredientNutrientResponse.setJoinId(ingredientNutrient.getId());
				ingredientNutrientResponse.setQuantity(ingredientNutrient.getQuantity());
				return ingredientNutrientResponse;
			}).collect(Collectors.toList());
			ingredientResponse.setNutrients(ingredientNutrientResponses);
		}
		if(includeAllergens)
		{
			List<IngredientAllergen> ingredientAllergens = ingredientAllergenService.findByIngredientId(ingredient.getId());
			List<GetIngredientAllergenResponse> ingredientAllergenResponses = ingredientAllergens.stream().map(ingredientAllergen -> {
				return modelMapper.map(ingredientAllergen.getAllergen(), GetIngredientAllergenResponse.class);
			}).collect(Collectors.toList());
			ingredientResponse.setAllergens(ingredientAllergenResponses);
		}
		return ingredientResponse;
	}
}
