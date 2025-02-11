package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import com.example.demo.entities.Users;
import com.example.demo.exception.UserAlreadyExists;
import com.example.demo.exception.UserNotFoundExceptions;
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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        List<GrantedAuthority> authorities = Collections.singletonList(authority);
        return new User(user.getUserName(), user.getPassword(), authorities);
    }

    // Method List All Users.
    public List<UserDto> getAllUser() {
        List<Users> userList = userRepo.findAll();
        return mapList(userList, UserDto.class, modelMapper);
    }

    public UserInfoDto getUserById(Integer id) {
        Users users = userRepo.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundExceptions("User not found ID: " + id));
        return modelMapper.map(users, UserInfoDto.class);
    }

    // Method Add User.
    public Users create(AddUserDto newUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserRole = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse(null);

        if ("ROLE_USER".equals(currentUserRole)) {
            throw new AccessDeniedException("You do not have permission to create users.");
        }

        Users existUsers = userRepo.findByUserName(newUser.getUserName());
        if (existUsers == null) {

            Users user = modelMapper.map(newUser, Users.class);
            // If user not input role -> Default set role to user.
            if (newUser.getRole() != null) {
                user.setRole(Roles.valueOf(newUser.getRole().toUpperCase()));
            } else {
                user.setRole(Roles.USER);
            }

            user.setPassword(encoder.encode(newUser.getPassword()));
            Users saveUser = userRepo.saveAndFlush(user);
            saveUser.setPassword("**********");
            return saveUser;
        } else
            throw new UserAlreadyExists("User already exist.");
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
