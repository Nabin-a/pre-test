package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
    Users findByRole(Roles roles);
}
