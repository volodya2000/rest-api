package com.udemy.restudemy.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException serviceException, WebRequest webRequest)
    {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),serviceException.getMessage());

        return new  ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})

    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex,WebRequest webRequest)
    {
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage());

        return new  ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
