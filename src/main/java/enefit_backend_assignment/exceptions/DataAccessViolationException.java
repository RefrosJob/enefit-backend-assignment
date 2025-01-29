package enefit_backend_assignment.exceptions;

public class DataAccessViolationException extends RuntimeException {
    public DataAccessViolationException(String reason) {
        super(reason);
    }
}