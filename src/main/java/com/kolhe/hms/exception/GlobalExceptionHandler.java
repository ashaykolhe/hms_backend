package com.kolhe.hms.exception;

import com.kolhe.hms.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setHttpStatue(HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage("Validation errors.");
        response.setHttpStatue(HttpStatus.BAD_REQUEST.name());
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String, String> errors = new HashMap<>();
        allErrors.forEach(error -> {
            FieldError error1 = (FieldError) error;
            errors.put(error1.getField(), error1.getDefaultMessage());
        });
        response.setErrors(errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        String constraintName = ((ConstraintViolationException) ex.getCause()).getSQLException().getMessage();
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMostSpecificCause().getMessage());
        response.setHttpStatue(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return ResponseEntity.internalServerError().body(response);
    }

}
