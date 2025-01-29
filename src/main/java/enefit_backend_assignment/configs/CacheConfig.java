package enefit_backend_assignment.configs;

import enefit_backend_assignment.helpers.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    public static final String MARKET_DATA = "MARKET_DATA";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(MARKET_DATA);
    }

    @CacheEvict(allEntries = true, value = {MARKET_DATA})
    @Scheduled(fixedDelay = 10 * 60 * 1000 ,  initialDelay = 500)
    public void reportCacheEvict() {
        Logger.infoLogger().log("Flush Cache {}", ZonedDateTime.now());
    }

}
