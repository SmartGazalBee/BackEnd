package Matdol.SmartGazalBee.DeviceComparison.Dao;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import Matdol.SmartGazalBee.DeviceComparison.Repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeviceDaoImpl implements DeviceDao{

    private final DeviceRepository deviceRepository;
    @Override
    public Device findById(Long id) {
        return deviceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException ());
    }

    @Override
    public Slice<Device> firstFindDeviceAll(Pageable pageable) {
        return deviceRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Slice<Device> remainfindDeviceAll(Long id, Pageable pageable) {
        return deviceRepository.findByIdLessThanOrderByIdDesc(id,pageable);
    }

    @Override
    public Boolean existByIdLessThan(Long id) {
        return deviceRepository.existsByIdLessThan(id);
    }

    @Override
    public Slice<Device> firstSearchDevice(String name, Pageable pageable) {
        return deviceRepository.findByNameContainingOrderByIdDesc(name,pageable);
    }

    @Override
    public Slice<Device> remainSearchDevice(String name, Long id, Pageable pageable) {
        return deviceRepository.findByIdLessThanAndNameContainingOrderByIdDesc(id, name, pageable);
    }
}
