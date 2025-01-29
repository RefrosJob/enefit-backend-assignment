package enefit_backend_assignment.repositories;

import enefit_backend_assignment.repositories.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Boolean existsByUsername(String username);
    Customer findByUsernameAndPassword(String username, String password);
    Customer findByUsername(String username);
}
