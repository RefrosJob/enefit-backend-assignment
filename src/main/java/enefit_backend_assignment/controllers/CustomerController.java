package enefit_backend_assignment.controllers;

import enefit_backend_assignment.entities.tables.Customer;
import enefit_backend_assignment.repositories.CustomerRepository;
import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()).getId();
    }

    @GetMapping("is-registered")
    Boolean isRegistered(@RequestParam String username) {
        return customerRepository.existsByUsername(username);
    }

    @GetMapping("/customers")
    List<Customer> customers() {
        return customerRepository.findAll();
    }

    @PostMapping("/register")
    UUID registerCustomer(@RequestBody String username, @RequestBody String password, @RequestBody String confirmPassword, @Nullable @RequestBody String first_name, @Nullable @RequestBody String last_name) {
        //TODO: Handle unique username constraint error
        return customerRepository.save(new Customer(username, password, confirmPassword, first_name, last_name)).getId();
    }
}
