package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.UserDTO;
import com.example.productmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) { //login nu tine de user controller ci de spring security
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) { //creaza un user nou
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
