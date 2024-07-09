package Matdol.SmartGazalBee.DeviceComparison.Service;

import Matdol.SmartGazalBee.DeviceComparison.Dao.DeviceDao;
import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private final DeviceDao deviceDao;
    @Override
    public Device getDeviceInfo(Long id) {
        return deviceDao.findById(id);
    }
}
