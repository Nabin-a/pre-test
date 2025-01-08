package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.entities.Users;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    // GET Method: List all users information
    @GetMapping("/list")
    public List<UserDto> listAllUser() {
        return userService.getAllUser();
    }

    // GET {id} Method: Display details of user.
    @GetMapping("/detail/{id}")
    public UserInfoDto detail(@PathVariable int id) {
        return userService.getUserById(id);
    }
    

    //POST Method: Create new user
    @PostMapping("/add-user")
    @ResponseStatus(HttpStatus.CREATED)
    public Users create(@Valid @RequestBody AddUserDto newUser) {
        return userService.create(newUser);
    }

    //PATCH Method: Edit some of field user by id
    @PatchMapping("/{id}")
    public UserInfoDto edit(@PathVariable int id, @Valid @RequestBody Map<Object, Object> fields){
        return userService.editUser(id, fields);
    }
    
    //DELETE Method: Remove user by id
    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Integer id) {
        userService.removeUser(id);
    }
}
