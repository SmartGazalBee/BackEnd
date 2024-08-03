package Matdol.SmartGazalBee.DeviceComparison.Service;

import Matdol.SmartGazalBee.DeviceComparison.Dao.DeviceDao;
import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{
    private final int defaultPageSize = 3;

    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    private final DeviceDao deviceDao;
    @Override
    public Device getDeviceInfo(Long id) {
        return deviceDao.findById(id);
    }

    @Override
    public Slice<Device> getDeviceAll(Long id) {
        Pageable pageable = PageRequest.of(0,defaultPageSize);
        return getDevices(id,pageable);
    }
    private Slice<Device> getDevices(Long id, Pageable pageable)
    {
        return id == null ?
                deviceDao.firstFindDeviceAll(pageable) :
                deviceDao.remainfindDeviceAll(id,pageable);
    }

    @Override
    public Slice<Device> getSearchDevice(String name, Long id) {
        Pageable pageable = PageRequest.of(0,defaultPageSize);
        return getDevices(id,name,pageable);
    }
    private Slice<Device> getDevices(Long id, String name ,Pageable pageable)
    {
        return id == null ?
                deviceDao.firstSearchDevice(name,pageable):
                deviceDao.remainSearchDevice(name,id,pageable);
    }


}
