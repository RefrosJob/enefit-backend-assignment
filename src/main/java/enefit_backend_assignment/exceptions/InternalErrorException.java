package enefit_backend_assignment.exceptions;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String reason) {
        super(reason);
    }
}