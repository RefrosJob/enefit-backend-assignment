package enefit_backend_assignment.services;

import enefit_backend_assignment.configs.CacheConfig;
import enefit_backend_assignment.helpers.Logger;
import enefit_backend_assignment.services.models.MarketData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MarketDataService {
    private static final String url = "https://estfeed.elering.ee/api/public/v1/energy-price/electricity?startDateTime={startDateTime}&endDateTime={endDateTime}&resolution={resolution}";
    private static final String resolution = "one_month";

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(CacheConfig.MARKET_DATA)
    public MarketData[] getMonthlyMarketDataPerYear(int year) {
        Map<String, String> marketDataQuery = new HashMap<>();
        try {
            marketDataQuery.put("startDateTime", DateTimeFormatter.ISO_DATE_TIME.format(ZonedDateTime.of(year, 1, 1, 1, 1, 1, 1, ZoneOffset.of("Z"))));
            marketDataQuery.put("endDateTime", DateTimeFormatter.ISO_DATE_TIME.format(ZonedDateTime.of(year + 1, 1, 1, 1, 1, 1, 1, ZoneOffset.of("Z"))));
            marketDataQuery.put("resolution", resolution);

            ResponseEntity<MarketData[]> responseEntity = restTemplate.getForEntity(url, MarketData[].class, marketDataQuery);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            Logger.errorLogger().log("MarketDataService getMarketData error", e);
            throw e;
        }
    }
}
