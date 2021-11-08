package com.eshop.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoTokenException extends Exception{
    public NoTokenException() {
    }

    public NoTokenException(String message) {
        super(message);
    }
}
