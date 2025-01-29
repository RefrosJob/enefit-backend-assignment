package enefit_backend_assignment;

import enefit_backend_assignment.helpers.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = {"enefit_backend_assignment.repositories"})
public class EnefitBackendAssignmentApplication {
    public static void main(String[] args) {
        Logger.infoLogger().log("Application started at port 8080");
        SpringApplication.run(EnefitBackendAssignmentApplication.class, args);
    }
}
