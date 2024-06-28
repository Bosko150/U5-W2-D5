package francescocossu.u5w2d5.entities;

public enum DeviceStatus {
    DISPOSITIVO_DISPONIBILE,
    DISPOSITIVO_ASSEGNATO,
    DISPOSITIVO_MANUTENZIONE,
    DISPOSITIVO_DISMESSO;


    public static DeviceStatus getDeviceStatus(String deviceStatus) {
        return switch (deviceStatus.toUpperCase()) {
            case "DISPOSITIVO_DISPONIBILE" -> DeviceStatus.DISPOSITIVO_DISPONIBILE;
            case "DISPOSITIVO_ASSEGNATO" -> DeviceStatus.DISPOSITIVO_ASSEGNATO;
            case "DISPOSITIVO_MANUTENZIONE" -> DeviceStatus.DISPOSITIVO_MANUTENZIONE;
            case "DISPOSITIVO_DISMESSO" -> DeviceStatus.DISPOSITIVO_DISMESSO;

            default -> null;
        };

    }
}
