package com.gusdev.recipemenu.infra.exception.enums;

public enum ErrorCode {

    ON0001("Invalid password.", "ON-0001"),
    ON0002("Invalid Jwt Token.", "ON-0002"),
    WA0001("This user was not found.", "WA-0001"),
    WA0002("This recipe was not found.", "WA-0002")
    ;
    private String message;
    private String code;

    ErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
