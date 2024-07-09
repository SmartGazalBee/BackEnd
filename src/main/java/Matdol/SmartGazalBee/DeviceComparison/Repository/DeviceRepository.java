package Matdol.SmartGazalBee.DeviceComparison.Repository;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {

}
