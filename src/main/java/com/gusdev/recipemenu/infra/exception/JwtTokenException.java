package com.gusdev.recipemenu.infra.exception;

public class JwtTokenException extends RuntimeException {
    private String code;

    public JwtTokenException(String code, String message) {
        super(message);
        this.code = code;
    }
}
