package francescocossu.u5w2d5.entities;

public enum DeviceType {
    SMARTPHONE,
    TABLET,
    LAPTOP;

    public static DeviceType getDeviceType(String deviceType) {
        return switch (deviceType.toUpperCase()) {
            case "SMARTPHONE" -> DeviceType.SMARTPHONE;
            case "TABLET" -> DeviceType.TABLET;
            case "LAPTOP" -> DeviceType.LAPTOP;
            default -> null;
        };
    }
}
