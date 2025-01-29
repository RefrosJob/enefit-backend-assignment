package enefit_backend_assignment.controllers.advice;

import enefit_backend_assignment.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class DataControllerAdvice {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> dataCollectionErrorHandler(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateErrorDescription(ex.getMessage()));
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<String> dataCollectionInternalErrorHandler(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorDescription(ex.getMessage()));
    }

    @ExceptionHandler(DataAccessViolationException.class)
    public ResponseEntity<String> dataAccessViolationExceptionHandler(DataAccessViolationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(generateErrorDescription(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorDescription(ex.getMessage()));
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<String> uniqueConstraintViolationExceptionHandler(UniqueConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(generateErrorDescription(ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationExceptionHandler(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(generateErrorDescription(ex.getMessage()));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> restClientExceptionHandler(RestClientException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorDescription(ex.getMessage()));
    }

    private String generateErrorDescription(String description) {
        return "{\"error_description\":\"" + description + "\"}";
    }

}
