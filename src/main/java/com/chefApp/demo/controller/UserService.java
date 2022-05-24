package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository r;

    public List<User> getAll() { return r.findAll(); }

    public Optional<User> getOne(long id) {
        return r.findById(id);
    }

    public User createOne(User user) {
        return r.save(user);
    }


}
