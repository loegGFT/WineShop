package com.example.wineshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler({TypeNotFoundException.class,RuntimeException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String TypeNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }
}