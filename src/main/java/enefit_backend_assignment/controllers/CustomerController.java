package enefit_backend_assignment.controllers;

import enefit_backend_assignment.controllers.request.records.CustomerCreationRecord;
import enefit_backend_assignment.exceptions.AuthenticationException;
import enefit_backend_assignment.exceptions.UniqueConstraintViolationException;
import enefit_backend_assignment.helpers.Logger;
import enefit_backend_assignment.repositories.CustomerRepository;
import enefit_backend_assignment.repositories.entities.Customer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    UUID login(@RequestBody Customer customer) {
        Logger.infoLogger().log("Login attempt for customer: {}", customer);
        Customer foundCustomer = customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (foundCustomer == null) {
            throw new AuthenticationException("Invalid credentials");
        }
        return foundCustomer.getId();
    }

    @GetMapping("/is-registered")
    Boolean isRegistered(@RequestParam String username) {
        return customerRepository.existsByUsername(username);
    }

    @PostMapping("/register")
    UUID registerCustomer(@RequestBody CustomerCreationRecord customer) {
        Logger.infoLogger().log("Registering new customer with username: {}", customer.username());
        try {
            return customerRepository.save(
                    new Customer(customer.username(), customer.password(), customer.confirmPassword(),
                            customer.firstName(), customer.lastName())
            ).getId();
        } catch (DataIntegrityViolationException e) {
            Logger.errorLogger().log("Customer {} already exists", customer.username());
            throw new UniqueConstraintViolationException("This username is already taken");
        }
    }
}
