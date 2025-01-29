package enefit_backend_assignment.repositories;

import enefit_backend_assignment.repositories.entities.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface ConsumptionRepository extends JpaRepository<Consumption, UUID> {
    List<Consumption> findAllByMeteringPointId(UUID meteringPointId);
}
