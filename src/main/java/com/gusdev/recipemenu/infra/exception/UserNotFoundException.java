package com.gusdev.recipemenu.infra.exception;

public class UserNotFoundException extends RuntimeException{
    private String code;

    public UserNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
