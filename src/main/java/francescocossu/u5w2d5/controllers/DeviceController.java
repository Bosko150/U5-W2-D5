package francescocossu.u5w2d5.controllers;


import francescocossu.u5w2d5.entities.Device;
import francescocossu.u5w2d5.exceptions.BadRequestException;
import francescocossu.u5w2d5.payloads.DeviceDTO;
import francescocossu.u5w2d5.payloads.UpdateDeviceDTO;
import francescocossu.u5w2d5.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;


    @GetMapping
    private Page<Device> getDevices() {
        return deviceService.getAllDevices(0, 20, "deviceType");
    }

    @GetMapping("/{id}")
    private Device getDeviceById(UUID id) {
        return deviceService.getDeviceById(id);
    }


    @PostMapping
    private Device saveDevice(@RequestBody @Validated DeviceDTO devicePayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
        }
        return deviceService.saveDevice(devicePayload);

    }


    @PutMapping("/{id}")
    public Device updateDeviceById(@PathVariable UUID id, @RequestBody @Validated UpdateDeviceDTO devicePayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return deviceService.getByDeviceIdAndUpdate(id, devicePayload);
    }

    @DeleteMapping("/{id}")
    private void deleteDeviceById(UUID id) {
        deviceService.deleteDeviceById(id);
    }

    @PatchMapping("/{id}/assign")
    public Device assignDeviceToEmployee(@PathVariable UUID id, @RequestParam UUID employeeId) {
        return deviceService.assignDeviceToEmployee(id, employeeId);
    }
}
