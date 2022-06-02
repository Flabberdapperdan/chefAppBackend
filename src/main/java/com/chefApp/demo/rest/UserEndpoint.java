package com.chefApp.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chefApp.demo.model.User;
import com.chefApp.demo.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserEndpoint {
	@Autowired
	private UserService userService;

    Logger logger = Logger.getLogger(UserEndpoint.class.getName());

	@GetMapping
	public List<User> getAllUsers(){
		return userService.readAll();
	}

	@GetMapping({"{id}"})
	public User getUserById(@PathVariable("id") long id) {
		return userService.read(id).orElse(null);
	}
				
	@PostMapping
	public User createUser(@RequestBody User user){
		//Validation
		User createdAllergen = user;
		return userService.create(createdAllergen);
	}
	
	@PutMapping("{id}")
	public User updateUserById(@PathVariable("id") long id, @RequestBody User user){
		Optional<User> optionalUser = userService.read(id);
		if(optionalUser.isPresent())
		{
			//Validation
			//Update properties
			User updatedUser = user;
			updatedUser.setId(id);
			return userService.update(updatedUser);
		}
		else
		{
			return null;
		}
	}
	
	@DeleteMapping("{id}")
	public boolean deleteUserById(@PathVariable("id")long id) {
		Optional<User> optionalUser = userService.read(id);
		if(optionalUser.isPresent())
		{
			userService.delete(id);
			return true;
		}
		else
		{
			return false;
		}
	}
}
