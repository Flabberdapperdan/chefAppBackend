package com.chefApp.demo.service;

import com.chefApp.demo.model.Ingredient;
import com.chefApp.demo.model.User;
import com.chefApp.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.ValidationException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    Logger logger = Logger.getLogger(UserService.class.getName());

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public Optional<User> read(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User create(User user) {
        //Data Access Verification
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user)
    {
        //Data Access Verification
        return userRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
