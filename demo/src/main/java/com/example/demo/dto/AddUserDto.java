package com.example.demo.dto;

import java.sql.Date;

import jakarta.persistence.Column;
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

    private String role;

    private Date dateOfBirth;
}
