package Matdol.SmartGazalBee.DeviceComparison.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.Paging.SliceResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import Matdol.SmartGazalBee.DeviceComparison.Mapper.DeviceMapper;
import Matdol.SmartGazalBee.DeviceComparison.Service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class DeviceComparisonController {
    @Autowired
    private final DeviceService deviceService;

    private final DeviceMapper deviceMapper;

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<ResponseBody<Device>> findDeviceInfo(@PathVariable Long deviceId)
    {
        Device device = deviceService.getDeviceInfo(deviceId);
        return BeeResponse.toResponse(Status.FIND,device);
    }

    @GetMapping("/devices")
    public ResponseEntity<ResponseBody<SliceResponse<?>>> getDeviceAll(@RequestParam(value = "cursor", required = false)Long cursor)
    {
        Slice<Device> deviceAll = deviceService.getDeviceAll(cursor);
        SliceResponse<Device> sliceResponse = deviceMapper.toDto(deviceAll);
        return BeeResponse.toResponse(Status.FIND,sliceResponse);
    }
}
