package Matdol.SmartGazalBee.DeviceComparison.Service;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import Matdol.SmartGazalBee.DeviceComparison.Dto.DeviceDto;
import org.springframework.data.domain.Slice;

public interface DeviceService {
    DeviceDto getDeviceInfo(Long id);
    Slice<Device> getDeviceAll(Long id);

    Slice<Device> getSearchDevice(String name, Long id);
}
