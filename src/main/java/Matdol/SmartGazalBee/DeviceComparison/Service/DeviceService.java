package Matdol.SmartGazalBee.DeviceComparison.Service;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DeviceService {
    Device getDeviceInfo(Long id);
    Slice<Device> getDeviceAll(Long id);
}
