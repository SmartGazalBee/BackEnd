package Matdol.SmartGazalBee.DeviceComparison.Dao;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface DeviceDao {
    Device findById(Long id);

    Slice<Device> firstFindDeviceAll(Pageable pageable);
    Slice<Device> remainfindDeviceAll(Long id, Pageable pageable);

    Boolean existByIdLessThan(Long id);
}
