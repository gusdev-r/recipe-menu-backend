package com.gusdev.recipemenu.infra.handlerException;

import com.gusdev.recipemenu.domain.Recipe;
import com.gusdev.recipemenu.infra.exception.RecipeNotFoundException;
import com.gusdev.recipemenu.infra.exception.UserNotFoundException;
import com.gusdev.recipemenu.infra.exception.enums.ErrorCode;
import com.gusdev.recipemenu.infra.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    private ResponseError parkingSpaceNotFound(UserNotFoundException parkingSpaceNotFoundException) {
        return new ResponseError(HttpStatus.NOT_FOUND, false,
                ErrorCode.WA0001.getCode(), ErrorCode.WA0001.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(RecipeNotFoundException.class)
    private ResponseError recipeNotFound(RecipeNotFoundException recipeNotFoundException) {
        return new ResponseError(HttpStatus.NOT_FOUND, false,
                ErrorCode.WA0002.getCode(), ErrorCode.WA0002.getMessage());
    }
}
