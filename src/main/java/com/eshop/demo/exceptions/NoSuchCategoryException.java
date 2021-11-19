package com.eshop.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSuchCategoryException extends Exception {
    public NoSuchCategoryException() {
    }

    public NoSuchCategoryException(String message) {
        super(message);
    }
}
