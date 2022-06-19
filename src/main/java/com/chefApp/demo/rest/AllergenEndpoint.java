package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.CreateAllergenRequest;
import com.chefApp.demo.dto.GetAllergenResponse;
import com.chefApp.demo.dto.UpdateAllergenRequest;
import com.chefApp.demo.model.Allergen;
import com.chefApp.demo.service.AllergenService;

@RestController
@RequestMapping("api/allergens")
public class AllergenEndpoint {
	@Autowired
	private AllergenService allergenService;

	private ModelMapper modelMapper = new ModelMapper();
	Logger logger = Logger.getLogger(AllergenEndpoint.class.getName());

	@GetMapping
	public List<GetAllergenResponse> getAllAllergens(){
		List<Allergen> allergens = allergenService.readAll();
		return allergens.stream().map(allergen -> {
			return modelMapper.map(allergen, GetAllergenResponse.class);
        }).collect(Collectors.toList());
	}

	@GetMapping({"{id}"})
	public GetAllergenResponse getAllergenById(@PathVariable("id") long id) {
		Optional<Allergen> optionalAllergen = allergenService.read(id);
		if(optionalAllergen.isPresent())
		{
			return modelMapper.map(optionalAllergen.get(), GetAllergenResponse.class);
		}
		else
		{
			return null;
		}
	}
				
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GetAllergenResponse createAllergen(@RequestBody CreateAllergenRequest allergenRequest){
		//Validation
		Allergen allergen = modelMapper.map(allergenRequest, Allergen.class);
		return modelMapper.map(allergenService.create(allergen), GetAllergenResponse.class);
	}
	
	@PutMapping("{id}")
	public Allergen updateAllergenById(@PathVariable("id") long id, @RequestBody UpdateAllergenRequest allergenRequest){
		// add a modelmapper wrapper soon
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		Optional<Allergen> optionalAllergen = allergenService.read(id);
		if(optionalAllergen.isPresent())
		{
			//Validation
			//Update properties
			Allergen allergen = optionalAllergen.get();
			modelMapper.map(allergenRequest, allergen);
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return allergenService.update(allergen);
		}
		else
		{
			modelMapper.getConfiguration().setSkipNullEnabled(false);
			return null;
		}
	}
	
	@DeleteMapping("{id}")
	public Allergen deleteAllergenById(@PathVariable("id")long id) {
		Optional<Allergen> optionalAllergen = allergenService.read(id);
		if(optionalAllergen.isPresent())
		{
			allergenService.delete(id);
		}
		return optionalAllergen.orElse(null);
	}
}
	

