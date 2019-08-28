package com.oib.springoibproject.controllers;

import com.oib.springoibproject.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public String handleNotFound(NotFoundException e) {
        return e.getMessage();
    }
}
