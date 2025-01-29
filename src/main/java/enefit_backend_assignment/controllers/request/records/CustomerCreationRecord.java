package enefit_backend_assignment.controllers.request.records;

public record CustomerCreationRecord(String firstName, String lastName, String password, String username, String confirmPassword) {}
