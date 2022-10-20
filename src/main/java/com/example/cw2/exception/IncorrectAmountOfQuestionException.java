package com.example.cw2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectAmountOfQuestionException extends RuntimeException{
    public IncorrectAmountOfQuestionException(String s) {
        super(s);
    }
}
