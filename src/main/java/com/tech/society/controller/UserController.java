package com.tech.society.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.society.dto.UserDto;
import com.tech.society.service.RegistrationService;
import com.tech.society.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ResidentService userService;

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/healthCheck")
    public ResponseEntity<String> healthChek() {
        return ResponseEntity.ok("Hello, Welcome to Resident Service..!!");
    }

    @GetMapping("/getUserObjectStructure")
    public ResponseEntity<String> getUserObjectStructure() {
        ObjectMapper obj = new ObjectMapper();
        String json = null;
        try {
            json = obj.writeValueAsString(new UserDto( "id", "String name", "String email", "String phone", "String role"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("User Object Structure is "+json );
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        registrationService.onUserAdded(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
