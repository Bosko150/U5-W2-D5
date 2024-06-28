package francescocossu.u5w2d5.payloads;

import jakarta.validation.constraints.NotEmpty;

public record DeviceDTO(
        @NotEmpty(message = "Device type cannot be empty")
        String deviceType) {
}
