package com.chefApp.demo.rest;

import com.chefApp.demo.controller.UserService;
import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserEndpoint {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List> getAll() {
        List<User> usersList = service.getAll();
        if (!usersList.isEmpty()) {
            return new ResponseEntity<>(usersList, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getOne(@PathVariable long id) {
        if (id >= 0) {
            Optional<User> userOptional = service.getOne(id);
            if (userOptional.isPresent()) {
                return new ResponseEntity<>(userOptional.get(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(this.service.createOne(user),
                HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateById(@PathVariable long id, @RequestBody User input) {
        Optional<User> oldUserOptional = this.service.getOne(id);
        if (id >= 0) {
            if (oldUserOptional.isPresent()) {
                User updated = (User) this.service.updateOne(input, id);
                return new ResponseEntity<>(updated, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    

}
