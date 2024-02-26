package com.sumerge.kafkaapp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class EmployeeExceptionHandler {





    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleGenericMovieException(Exception exc)
    {

        EmployeeErrorResponse errorResponse= new EmployeeErrorResponse();


        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exception)
    {
        EmployeeErrorResponse errorResponse=new EmployeeErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage("The employee with id "+exception.getId()+" was not found!");
        errorResponse.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }


}
