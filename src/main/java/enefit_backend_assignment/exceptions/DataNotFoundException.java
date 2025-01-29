package enefit_backend_assignment.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String reason) {
        super(reason);
    }
}