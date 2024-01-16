
package com.example.productservice.error;

import com.example.productservice.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class ExceptionHandlers
{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResponse validation(MethodArgumentNotValidException exception){
        Map<String,String> errors=new HashMap<>();
        exception.getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return new APIResponse(HttpStatus.BAD_REQUEST.value(),errors,"errors in the given data", false);
    }
}

