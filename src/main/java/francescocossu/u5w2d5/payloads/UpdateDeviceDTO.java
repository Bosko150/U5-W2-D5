package francescocossu.u5w2d5.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UpdateDeviceDTO(@NotEmpty(message = "Device type cannot be empty") String deviceType,
                              @NotEmpty(message = "Device status cannot be empty") String deviceStatus,
                              UUID employeeID) {
}
