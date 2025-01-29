package enefit_backend_assignment.controllers;

import enefit_backend_assignment.exceptions.DataAccessViolationException;
import enefit_backend_assignment.helpers.Logger;
import enefit_backend_assignment.repositories.CustomerRepository;
import enefit_backend_assignment.repositories.MeteringPointRepository;
import enefit_backend_assignment.repositories.entities.Customer;
import enefit_backend_assignment.repositories.entities.MeteringPoint;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ConsumptionController {
    private final MeteringPointRepository meteringPointRepository;
    private final CustomerRepository customerRepository;

    ConsumptionController(CustomerRepository customerRepository, MeteringPointRepository meteringPointRepository) {
        this.meteringPointRepository = meteringPointRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{username}/consumption")
    public List<MeteringPoint> getConsumption(@PathVariable String username, @RequestParam UUID userId) {
        Logger.infoLogger().log("Getting Consumption for user " + username);
        Customer customer = customerRepository.findByUsername(username);
        UUID customerId = customer.getId();
        if (!customerId.equals(userId)) {
            Logger.errorLogger().log("User " + username + " does not belong to the customer " + userId);
            throw new DataAccessViolationException("Data access violation");
        }
        return meteringPointRepository.findAllByCustomerId(customerId);
    }
}
