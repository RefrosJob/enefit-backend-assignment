package enefit_backend_assignment.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@Entity
@Table(schema = "migrations", name = "metering_points")
public class MeteringPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String address;
    @Column
    @JsonIgnore
    private UUID customerId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "meteringPointId")
    @JsonManagedReference
    private List<Consumption> consumptions;

    public MeteringPoint() {}

    @Override
    public String toString() {
        return "MeteringPoint{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                ", consumptions=" + consumptions +
                '}';
    }


    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }
}
