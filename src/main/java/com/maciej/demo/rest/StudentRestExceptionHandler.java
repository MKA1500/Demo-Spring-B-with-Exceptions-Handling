package com.maciej.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleNotFoundException(StudentNotFoundException e) {
    StudentErrorResponse errorResponse = new StudentErrorResponse();
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setMessage(e.getMessage());
    errorResponse.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleOtherExceptions(Exception e) {
    StudentErrorResponse errorResponse = new StudentErrorResponse();
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessage(e.getMessage());
    errorResponse.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
