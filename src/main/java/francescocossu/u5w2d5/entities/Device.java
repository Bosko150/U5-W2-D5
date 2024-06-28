package francescocossu.u5w2d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    @Enumerated(EnumType.STRING)
    private DeviceStatus deviceStatus;
    @ManyToOne
    @JoinColumn(name = "userID")
    private Employee employee;

    public Device(DeviceType deviceType, DeviceStatus deviceStatus) {
        this.deviceType = deviceType;
        this.deviceStatus = deviceStatus;
    }

}
