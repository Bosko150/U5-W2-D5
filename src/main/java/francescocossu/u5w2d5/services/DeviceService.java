package francescocossu.u5w2d5.services;


import francescocossu.u5w2d5.entities.Device;
import francescocossu.u5w2d5.exceptions.NotFoundException;
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

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Page<Device> getAllDevices(int pageNumbr, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumbr, pageSize, Sort.by(sortBy));

        return deviceRepository.findAll(pageable);

    }

    public Device getDeviceById(UUID id) {
        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Device getByDeviceIdAndUpdate(UUID id, Device updatedDevice) {
        Device foundDevice = getDeviceById(id);
        foundDevice.setDeviceType(updatedDevice.getDeviceType());
        foundDevice.setDeviceStatus(updatedDevice.getDeviceStatus());
        foundDevice.setEmployee(updatedDevice.getEmployee());
        return deviceRepository.save(foundDevice);
    }

    public void deleteDeviceById(UUID id) {
        deviceRepository.deleteById(id);
    }

}
