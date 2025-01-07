package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    // GET all users information.
    @GetMapping("/list")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }


    @PostMapping("/add-user")
    public UserDto create(@Validated @RequestBody AddUserDto newUser) {
        
        return userService.create(newUser);
    }
    

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Integer id) {
        userService.removeUser(id);
    }
}
