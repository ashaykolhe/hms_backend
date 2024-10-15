package com.kolhe.hms.service;

import com.kolhe.hms.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidatorService {
//    public static <T> ErrorResponse validate(T className, T dto) {
//        Validator validator;
//        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
//            validator = factory.getValidator();
//        }
//        Set<ConstraintViolation<T>> violations = validator.validate(dto);
//        if (!violations.isEmpty()) {
//            //something is wrong in request parameters
//            List<String> details = new ArrayList<>();
//            for (ConstraintViolation<T> violation : violations) {
//                details.add(violation.getMessage());
//            }
//            return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Request Error", details);
//        }
//        return null;
//    }
}
