package com.chefApp.demo.controller;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.ValidationException;

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

    public Object updateOne(User newUser, long id) {
        User oldUser = this.getOne(id).get();
        if (newUser.getName().length() > 0) {
            oldUser.setName(newUser.getName());
        } else {
            return new ValidationException();
        }
        if (newUser.getUserName().length() > 0) {
            oldUser.setUserName(newUser.getUserName());
        } else {
            return new ValidationException();
        }
        return r.save(oldUser);
    }


}
