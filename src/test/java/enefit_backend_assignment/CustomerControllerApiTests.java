package enefit_backend_assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import enefit_backend_assignment.repositories.CustomerRepository;
import enefit_backend_assignment.repositories.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerApiTests {
    private static final String END_POINT_PATH = "/login";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testIsRegistered() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/is-registered").param("username", "testuser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddShouldReturn401Unauthorized() throws Exception {
        Customer newUser = new Customer("unknown-username", "random-password");

        String requestBody = objectMapper.writeValueAsString(newUser);

        this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    public void testAddShouldReturnUUID() throws Exception {
        Customer existingUser = customerRepository.findAll().getFirst();
        Customer loginUser = new Customer(existingUser.getUsername(), existingUser.getPassword());

        String requestBody = objectMapper.writeValueAsString(loginUser);

        this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string('"' + existingUser.getId().toString() + '"'))
                .andDo(MockMvcResultHandlers.print())
        ;
    }

}
