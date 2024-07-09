package Matdol.SmartGazalBee.DeviceComparison.Dao;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;

import java.util.Optional;

public interface DeviceDao {
    Device findById(Long id);
}
