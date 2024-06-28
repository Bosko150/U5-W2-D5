package francescocossu.u5w2d5.entities;

import francescocossu.u5w2d5.exceptions.BadRequestException;

public enum DeviceType {
    SMARTPHONE,
    TABLET,
    LAPTOP;

    public static DeviceType getDeviceType(String deviceType) {
        return switch (deviceType.toUpperCase()) {
            case "SMARTPHONE" -> DeviceType.SMARTPHONE;
            case "TABLET" -> DeviceType.TABLET;
            case "LAPTOP" -> DeviceType.LAPTOP;
            default ->
                    throw new BadRequestException("Invalid device type. Must be either 'smartphone', 'tablet' or 'laptop'");
        };
    }
}
