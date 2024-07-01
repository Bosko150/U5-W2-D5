package francescocossu.u5w2d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(
        @NotEmpty(message = "Username cannot be empty")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,
        @NotEmpty(message = "Name cannot be empty")
        String name,
        @NotEmpty(message = "Surname cannot be empty")
        String surname,
        @NotEmpty(message = "Email cannot be empty")
        @Email
        String email,
        @NotEmpty(message = "Password cannot be empty")
        String password
) {
}
