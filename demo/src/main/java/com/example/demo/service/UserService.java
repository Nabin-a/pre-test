package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.AddUserDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.entities.Roles;
import com.example.demo.entities.UserPrincipal;
import com.example.demo.entities.Users;
import com.example.demo.repositories.UserRepository;

@Service

public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    // Method List All Users.
    public List<UserDto> getAllUser() {
        List<Users> userList = userRepo.findAll();
        return mapList(userList, UserDto.class, modelMapper);
    }

    public UserInfoDto getUserById(Integer id) {
        Users users = userRepo.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID " + id + " Does not exist"));
        return modelMapper.map(users, UserInfoDto.class);
    }

    // Method Add User.
    public Users create(AddUserDto newUser) {
        // If user not input role -> Default set role to user.
        Users user = modelMapper.map(newUser, Users.class);
        if (newUser.getRole() != null) {
            user.setRole(Roles.valueOf(newUser.getRole().toLowerCase()));
        } else {
            user.setRole(Roles.user);
        }

        user.setPassword(encoder.encode(newUser.getPassword()));
        Users saveUser = userRepo.saveAndFlush(user);
        saveUser.setPassword("**********");
        return saveUser;
    }

    // Method Edit User.
    public UserInfoDto editUser(int id, Map<Object, Object> fields) {
        Optional<Users> user = userRepo.findById(id);
        if (user.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Users.class, (String) key);
                if (field != null) {
                    field.setAccessible(true);
                    try {
                        if (field.getType() == java.sql.Date.class && value instanceof String) {
                            java.sql.Date dateValue = java.sql.Date.valueOf((String) value);
                            ReflectionUtils.setField(field, user.get(), dateValue);
                        } else {
                            ReflectionUtils.setField(field, user.get(), value);
                        }
                    } catch (IllegalArgumentException e) {
                        // Handle the exception (e.g., log it, rethrow it, etc.)
                        e.printStackTrace();
                    }
                }
            });
            return modelMapper.map(userRepo.saveAndFlush(user.get()), UserInfoDto.class);
        }
        return null;
    }

    // Method Remove User.
    public void removeUser(int id) {
        userRepo.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID " + id + " Does not exist"));

        userRepo.deleteById(id);
    }

    // Map list method
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }

    // Method login
    public String verify(LoginDto login) {
        Users user = modelMapper.map(login, Users.class);
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if (auth.isAuthenticated())
            return jwtService.generateToken(login.getUserName());

        return "fail";
    }
}
