package francescocossu.u5w2d5.controllers;


import francescocossu.u5w2d5.entities.Device;
import francescocossu.u5w2d5.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;


    @GetMapping
    private Page<Device> getDevices() {
        return deviceService.getAllDevices(0, 10, "name");
    }

    @GetMapping("/{id}")
    private Device getDeviceById(UUID id) {
        return deviceService.getDeviceById(id);
    }


    @PutMapping("/{id}")
    private Device updateDevice(UUID id, Device updatedDevice) {
        return deviceService.getByDeviceIdAndUpdate(id, updatedDevice);
    }

    @DeleteMapping("/{id}")
    private void deleteDeviceById(UUID id) {
        deviceService.deleteDeviceById(id);
    }

}
