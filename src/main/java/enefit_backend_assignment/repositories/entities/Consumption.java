package enefit_backend_assignment.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enefit_backend_assignment.exceptions.DataNotFoundException;
import enefit_backend_assignment.services.MarketDataService;
import enefit_backend_assignment.services.models.MarketData;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unused")
@Entity
@Table(schema = "migrations", name = "consumption")
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "metering_point_id")
    @JsonIgnore
    private UUID meteringPointId;
    @Column
    private float amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "amount_unit")
    private AmountUnit amountUnit;
    @Column(name = "consumption_time")
    private Timestamp consumptionTime;

    @Transient
    private float costEur;
    @Transient
    private float costEurVat;

    public Consumption() {
    }

    @PostLoad
    private void calculateConsumptionCosts() {
        int year = consumptionTime.toLocalDateTime().getYear();
        MarketData[] marketDataList = new MarketDataService().getMonthlyMarketDataPerYear(year);

        Optional<MarketData> currentMonthsMarketData = Arrays.stream(marketDataList)
                .filter(
                        marketData -> marketData.fromDateTime().toLocalDateTime().getMonth().equals(
                                consumptionTime.toLocalDateTime().getMonth()
                        )
                ).findFirst();

        if (currentMonthsMarketData.isEmpty()) {
            throw new DataNotFoundException("no actual market data");
        }

        switch (amountUnit) {
            case mwh:
                costEur = Math.round(amount * currentMonthsMarketData.get().eurPerMwh());
                costEurVat = amount * currentMonthsMarketData.get().eurPerMwhWithVat();
                break;
            case kwh:
                costEur = amount * currentMonthsMarketData.get().centsPerKwh() / 100;
                costEurVat = amount * currentMonthsMarketData.get().centsPerKwhWithVat() / 100;
                break;
        }
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", meteringPointId=" + meteringPointId +
                ", amount=" + amount +
                ", amountUnit=" + amountUnit +
                ", consumptionTime=" + consumptionTime +
                ", costEur=" + costEur +
                ", costEurVat=" + costEurVat +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public AmountUnit getAmountUnit() {
        return amountUnit;
    }

    public Timestamp getConsumptionTime() {
        return consumptionTime;
    }

    public float getCostEur() {
        return costEur;
    }

    public float getCostEurVat() {
        return costEurVat;
    }

    public enum AmountUnit {
        kwh,
        mwh
    }
}
