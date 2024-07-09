package Matdol.SmartGazalBee.DeviceComparison.Dao;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import Matdol.SmartGazalBee.DeviceComparison.Repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeviceDaoImpl implements DeviceDao{
    @Autowired
    private final DeviceRepository deviceRepository;
    @Override
    public Device findById(Long id) {
        return deviceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException ());
    }
}
