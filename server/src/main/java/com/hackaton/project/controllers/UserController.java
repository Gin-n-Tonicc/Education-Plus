package com.hackaton.project.controllers;

import com.hackaton.project.entities.User;
import com.hackaton.project.enums.UserRole;
import com.hackaton.project.exceptions.UserExistsException;
import com.hackaton.project.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public User[] getAll() {
        return userRepository.getAll();
    }

    @PostMapping
    public User submitUser(@Valid @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findOneByEmail(user.getEmail());
        user.setRole(UserRole.USER);

        if (optionalUser.isPresent()) {
            throw new UserExistsException("email");
        }

        return userRepository.save(user);
    }
}
