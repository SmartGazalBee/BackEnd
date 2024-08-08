package Matdol.SmartGazalBee.DeviceComparison.Dao;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DeviceDao {
    Device findById(Long id);

    Slice<Device> firstFindDeviceAll(Pageable pageable);
    Slice<Device> remainfindDeviceAll(Long id, Pageable pageable);

    Slice<Device> firstSearchDevice(String name,Pageable pageable);
    Slice<Device> remainSearchDevice(String name,Long id, Pageable pageable);
    Boolean existByIdLessThan(Long id);
}
