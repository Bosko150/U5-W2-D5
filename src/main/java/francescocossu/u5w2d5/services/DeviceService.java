package francescocossu.u5w2d5.services;


import francescocossu.u5w2d5.entities.Device;
import francescocossu.u5w2d5.entities.DeviceStatus;
import francescocossu.u5w2d5.entities.DeviceType;
import francescocossu.u5w2d5.entities.Employee;
import francescocossu.u5w2d5.exceptions.NotFoundException;
import francescocossu.u5w2d5.payloads.DeviceDTO;
import francescocossu.u5w2d5.payloads.UpdateDeviceDTO;
import francescocossu.u5w2d5.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    EmployeeService employeeService;

    public Device saveDevice(DeviceDTO device) {
        Device deviceToSave = new Device(DeviceType.getDeviceType(device.deviceType()), DeviceStatus.DISPOSITIVO_DISPONIBILE, null);

        return deviceRepository.save(deviceToSave);
    }

    public Page<Device> getAllDevices(int pageNumbr, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumbr, pageSize, Sort.by(sortBy));

        return deviceRepository.findAll(pageable);

    }

    public Device getDeviceById(UUID id) {
        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Device getByDeviceIdAndUpdate(UUID id, UpdateDeviceDTO updatedDevicePayload) {
        Device updatedDevice = new Device(DeviceType.getDeviceType(updatedDevicePayload.deviceType()), DeviceStatus.getDeviceStatus(updatedDevicePayload.deviceStatus()), updatedDevicePayload.employeeID() != null ? employeeService.getEmployeeById(updatedDevicePayload.employeeID()) : null);
        Device foundDevice = getDeviceById(id);
        foundDevice.setDeviceType(updatedDevice.getDeviceType());
        foundDevice.setDeviceStatus(updatedDevice.getDeviceStatus());
        foundDevice.setEmployee(updatedDevice.getEmployee());
        return deviceRepository.save(foundDevice);
    }

    public void deleteDeviceById(UUID id) {
        deviceRepository.deleteById(id);
    }

    public Device assignDeviceToEmployee(UUID deviceId, UUID employeeId) {
        Device device = getDeviceById(deviceId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (device.getDeviceStatus() == DeviceStatus.DISPOSITIVO_ASSEGNATO) {
            throw new IllegalArgumentException("Device already assigned");
        }

        device.setEmployee(employee);
        device.setDeviceStatus(DeviceStatus.DISPOSITIVO_ASSEGNATO);

        return deviceRepository.save(device);
    }

}
