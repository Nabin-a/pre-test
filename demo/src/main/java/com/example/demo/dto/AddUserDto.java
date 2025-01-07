package com.example.demo.dto;


import java.sql.Date;

import com.example.demo.entities.Roles;
import com.example.demo.validation.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {
    private String firstName;
    private String lastName;
    @NotBlank
    @Column(unique = true)
    private String userName;

    @NotBlank
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Role(enumClass = Roles.class)
    private String role;
    private Date dateOfBirth;
}
