package enefit_backend_assignment.entities.tables;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema = "migrations", name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column(name = "first_name")
    @Nullable
    private String firstName;
    @Column(name = "last_name")
    @Nullable
    private String lastName;

    public Customer() {}

    public Customer(String username, String password, String confirmPassword, @Nullable String firstName, @Nullable String lastName ) {
        if (!password.equals(confirmPassword)) {
            //TODO: Return Exception
            return;
        }
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
