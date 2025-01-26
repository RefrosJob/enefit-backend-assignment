package enefit_backend_assignment.config;

import enefit_backend_assignment.config.filters.AuthenticationFilter;
import enefit_backend_assignment.repositories.CustomerRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnefitBackendAssignmentConfig {

    private final CustomerRepository customerRepository;

    EnefitBackendAssignmentConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> headerValidatorFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>(getAuthenticationFilter());
        registrationBean.addUrlPatterns("/customers");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    public AuthenticationFilter getAuthenticationFilter() {
        return new AuthenticationFilter(customerRepository);
    }
}
