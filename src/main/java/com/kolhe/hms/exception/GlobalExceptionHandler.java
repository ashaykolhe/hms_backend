package com.kolhe.hms.exception;

import com.kolhe.hms.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return genericHandler(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ErrorResponse> handleUserCreationException(UserCreationException ex) {
        return genericHandler(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNameAlreadyTakenException.class)
    public ResponseEntity<ErrorResponse> handleUserNameAlreadyTakenException(UserNameAlreadyTakenException ex) {
        return genericHandler(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return genericHandler(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MobileAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleMobileAlreadyExistsException(MobileAlreadyExistsException ex) {
        return genericHandler(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> genericHandler(Exception ex, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setHttpStatus(httpStatus.name());
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage("Validation errors.");
        response.setHttpStatus(HttpStatus.BAD_REQUEST.name());
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
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return ResponseEntity.internalServerError().body(response);
    }

}
