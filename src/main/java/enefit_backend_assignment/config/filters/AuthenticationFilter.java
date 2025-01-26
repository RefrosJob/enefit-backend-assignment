package enefit_backend_assignment.config.filters;

import enefit_backend_assignment.repositories.CustomerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final CustomerRepository customerRepository;

    public AuthenticationFilter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
        UUID userId;
        String idParameter = request.getParameter("userId");

        if (idParameter == null) {
            handleInvalidAuthentication(response, "No user id parameter provided");
            return;
        }

        try {
            userId = UUID.fromString(idParameter);
        } catch (IllegalArgumentException exception) {
            handleInvalidAuthentication(response, "Invalid user id format");
            return;
        }

        if (userId.toString().isEmpty()) {
            handleInvalidAuthentication(response, "No user id provided");
            return;
        }

        if(!customerRepository.existsById(userId)) {
            handleInvalidAuthentication(response, "No customer with id " + userId + " exists");
        }

        chain.doFilter(request, response);
    }

    private void handleInvalidAuthentication(HttpServletResponse response, String description) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error_description\":\"" + description + "\"}");
    }
}
