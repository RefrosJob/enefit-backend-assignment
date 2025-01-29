package enefit_backend_assignment.exceptions;

public class UniqueConstraintViolationException extends DataAccessViolationException {
    public UniqueConstraintViolationException(String message) {
        super(message);
    }
}
