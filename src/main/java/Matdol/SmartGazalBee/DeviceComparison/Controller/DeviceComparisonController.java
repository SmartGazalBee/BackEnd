package Matdol.SmartGazalBee.DeviceComparison.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import Matdol.SmartGazalBee.DeviceComparison.Service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DeviceComparisonController {
    @Autowired
    private final DeviceService deviceService;

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<ResponseBody<Device>> findDeviceInfo(@PathVariable Long deviceId)
    {
        Device device = deviceService.getDeviceInfo(deviceId);
        return BeeResponse.toResponse(Status.FIND,device);
    }
}
