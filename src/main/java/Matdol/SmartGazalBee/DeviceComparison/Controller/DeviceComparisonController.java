package Matdol.SmartGazalBee.DeviceComparison.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.Paging.SliceResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import Matdol.SmartGazalBee.DeviceComparison.Dto.DeviceDto;
import Matdol.SmartGazalBee.DeviceComparison.Mapper.DeviceMapper;
import Matdol.SmartGazalBee.DeviceComparison.Service.DeviceService;
import Matdol.SmartGazalBee.DeviceComparison.Service.TranslationService;
import lombok.Getter;
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

    private final DeviceService deviceService;

    private final DeviceMapper deviceMapper;

    private final TranslationService translationService;

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<ResponseBody<DeviceDto>> findDeviceInfo(@PathVariable Long deviceId)
    {
        DeviceDto device = deviceService.getDeviceInfo(deviceId);
        return BeeResponse.toResponse(Status.FIND,device);
    }

    /*
        기기 전체 조회
        요청 방식 : 처음 상태엔 null 로 보냄 -> 가장 최신순에 기기 정보를 가져옴
                  이후엔 가장 id 값이 낮은 pk를 path로 요청함 그 이후에 정보들을 보냄

    */
    @GetMapping("/devices")
    public ResponseEntity<ResponseBody<SliceResponse<Device>>> getDeviceAll(@RequestParam(value = "cursor", required = false)Long cursor)
    {
        Slice<Device> deviceAll = deviceService.getDeviceAll(cursor);
        SliceResponse<Device> sliceResponse = deviceMapper.toDto(deviceAll);
        return BeeResponse.toResponse(Status.FIND,sliceResponse);
    }
    @GetMapping("devices/search")
    public ResponseEntity<ResponseBody<SliceResponse<Device>>> searchDevices(@RequestParam(value ="name") String name,
                                                                             @RequestParam(value = "cursor", required = false)Long cursor)
    {
        String translationDeviceName = translationService.translateToEnglish(name);
        Slice<Device> searchDevices = deviceService.getSearchDevice(translationDeviceName,cursor);
        SliceResponse<Device> sliceResponse = deviceMapper.toDto(searchDevices);
        return BeeResponse.toResponse(Status.FIND,sliceResponse);
    }

}



