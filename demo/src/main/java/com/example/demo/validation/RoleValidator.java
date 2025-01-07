package com.example.demo.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<Role, String> {
    private Set<String> roles;

    public static Set<String> getRolesSet(Class<? extends Enum<?>> e) {
        Enum<?>[] enums = e.getEnumConstants();
        String[] roles = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            roles[i] = enums[i].name();
        }
        Set<String> mySet = new HashSet<String>(Arrays.asList(roles));
        return mySet;
    }

    @Override
    public void initialize(Role role) {
        Class<? extends Enum<?>> enumSelected = role.enumClass();
        roles = getRolesSet(enumSelected);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else {
            return roles.contains(value);
        }
    }

}
