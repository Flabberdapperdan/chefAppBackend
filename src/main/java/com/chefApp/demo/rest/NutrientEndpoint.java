package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.dto.CreateNutrientRequest;
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
	public List<GetNutrientResponse> getAllNutrients(){
		List<Nutrient> nutrients = nutrientService.readAll();
		return nutrients.stream().map(nutrient -> {
			return modelMapper.map(nutrient, GetNutrientResponse.class);
        }).collect(Collectors.toList());
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
}
