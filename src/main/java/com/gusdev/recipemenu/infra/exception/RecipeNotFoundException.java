package com.gusdev.recipemenu.infra.exception;

public class RecipeNotFoundException extends RuntimeException {
    private String code;

    public RecipeNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
