package enefit_backend_assignment.services.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MarketData(float centsPerKwh, float centsPerKwhWithVat, float eurPerMwh, float eurPerMwhWithVat,
                         Timestamp fromDateTime, Timestamp toDateTime) {
}
