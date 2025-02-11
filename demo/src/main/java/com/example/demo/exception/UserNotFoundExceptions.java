package com.example.demo.exception;

public class UserNotFoundExceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundExceptions(String msg){
        super(msg);
    }
}
