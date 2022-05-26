package com.example.wineshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RegionNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler({RegionNotFoundException.class,RuntimeException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String RegionNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }
}
