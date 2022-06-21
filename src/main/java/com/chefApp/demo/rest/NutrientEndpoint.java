package com.chefApp.demo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.CreateNutrientRequest;
import com.chefApp.demo.dto.GetNutrientPageResponse;
import com.chefApp.demo.dto.GetNutrientResponse;
import com.chefApp.demo.dto.UpdateNutrientRequest;
import com.chefApp.demo.model.Nutrient;
import com.chefApp.demo.service.NutrientService;

@RestController
@RequestMapping("api/nutrients")
public class NutrientEndpoint {
	@Autowired
	private NutrientService nutrientService;

	private ModelMapper modelMapper = new ModelMapper();
	Logger logger = Logger.getLogger(NutrientEndpoint.class.getName());

	@GetMapping
	public GetNutrientPageResponse getAllNutrients(
		@RequestParam(value="page", defaultValue = "0") int page,
		@RequestParam(value="size", defaultValue = "10") int size,
		@RequestParam(value="sort_by", defaultValue = "id") String sortBy,
		@RequestParam(value="order_by", defaultValue = "asc") String orderBy 
		){
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.valueOf(orderBy.toUpperCase()), sortBy));
		Page<Nutrient> nutrientsPage = nutrientService.getAll(pageable);
		GetNutrientPageResponse nutrientPageResponse = new GetNutrientPageResponse();
		nutrientPageResponse.setCurrentPage(nutrientsPage.getNumber());
		nutrientPageResponse.setTotalPages(nutrientsPage.getTotalPages());
		nutrientPageResponse.setTotalItems(nutrientsPage.getTotalElements());
		nutrientPageResponse.setNutrients(nutrientsPage.stream().map(nutrient -> {
			return modelMapper.map(nutrient, GetNutrientResponse.class);
    	}).collect(Collectors.toList()));
		return nutrientPageResponse;
	}

	@GetMapping("{id}")
	public GetNutrientResponse getNutrientById(@PathVariable("id") long id) {
		Optional<Nutrient> optionalNutrient = nutrientService.read(id);
		if(optionalNutrient.isPresent())
		{
			return modelMapper.map(optionalNutrient.get(), GetNutrientResponse.class);
		}
		else
		{
			return null;
		}
	}
				
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GetNutrientResponse createNutrient(@RequestBody CreateNutrientRequest nutrientRequest){
		//Validation
		Nutrient nutrient = modelMapper.map(nutrientRequest, Nutrient.class);
		return modelMapper.map(nutrientService.create(nutrient), GetNutrientResponse.class);
	}
	
	@PutMapping("{id}")
	public Nutrient updateNutrientById(@PathVariable("id") long id, @RequestBody UpdateNutrientRequest nutrientRequest){
		// add a modelmapper wrapper soon
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		Optional<Nutrient> optionalNutrient = nutrientService.read(id);
		if(optionalNutrient.isPresent())
		{
			//Validation
			//Update properties
			Nutrient nutrient = optionalNutrient.get();
			modelMapper.map(nutrientRequest, nutrient);
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return nutrientService.update(nutrient);
		}
		else
		{
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return null;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteNutrientById(@PathVariable("id")long id) {
		Optional<Nutrient> optionalNutrient = nutrientService.read(id);
		if(optionalNutrient.isPresent())
		{
			nutrientService.delete(id);
			return true;
		}
		else
		{
			return false;
		}
	}

	///
	/// Validation Exception Handler
	///
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleValidationExceptions(
		ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations().forEach((violation) -> {
			String fieldName = violation.getPropertyPath().toString();
			String errorMessage = violation.getConstraintDescriptor().getMessageTemplate();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
