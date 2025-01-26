package enefit_backend_assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnefitBackendAssignmentApplication {
	private static final Logger log = LoggerFactory.getLogger(EnefitBackendAssignmentApplication.class);
	public static void main(String[] args) {
		log.atInfo().log("Application started at port 8080");
		SpringApplication.run(EnefitBackendAssignmentApplication.class, args);
	}

}
