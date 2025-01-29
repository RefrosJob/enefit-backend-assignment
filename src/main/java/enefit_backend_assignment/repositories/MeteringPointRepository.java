package enefit_backend_assignment.repositories;

import enefit_backend_assignment.repositories.entities.MeteringPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface MeteringPointRepository extends JpaRepository<MeteringPoint, UUID> {
    List<MeteringPoint> findAllByCustomerId(UUID customerId);
}
