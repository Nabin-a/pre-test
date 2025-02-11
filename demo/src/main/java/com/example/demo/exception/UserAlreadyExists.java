package com.example.demo.exception;

public class UserAlreadyExists extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserAlreadyExists(String msg) {
        super(msg);
    }
}
