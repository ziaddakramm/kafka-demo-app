package com.sumerge.kafkaapp.common.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeExceptionHandlerTest {

    @InjectMocks
    private EmployeeExceptionHandler exceptionHandler;

    @Mock
    private Exception genericException;

    @Mock
    private EmployeeNotFoundException notFoundException;

    @Test
    public void testHandleGenericEmployeeException() {

        when(genericException.getMessage()).thenReturn("Test generic exception");


        ResponseEntity<EmployeeErrorResponse> responseEntity = exceptionHandler.handleGenericMovieException(genericException);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Test generic exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandleEmployeeNotFoundException() {
        // Arrange
        when(notFoundException.getId()).thenReturn(0);

        ResponseEntity<EmployeeErrorResponse> responseEntity = exceptionHandler.handleEmployeeNotFoundException(notFoundException);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("The employee with id 0 was not found!", responseEntity.getBody().getMessage());
    }
}