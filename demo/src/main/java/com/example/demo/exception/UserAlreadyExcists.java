package com.example.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExcists extends RuntimeException {
    private String msg;

    public UserAlreadyExcists(String msg) {
        super(msg);
        this.msg = msg;
    }
}
