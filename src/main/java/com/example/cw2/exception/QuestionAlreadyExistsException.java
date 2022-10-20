package com.example.cw2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionAlreadyExistsException extends RuntimeException{
    public QuestionAlreadyExistsException(String s) {
        super(s);
    }
}
