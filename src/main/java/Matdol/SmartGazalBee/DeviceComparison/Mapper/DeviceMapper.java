package Matdol.SmartGazalBee.DeviceComparison.Mapper;

import Matdol.SmartGazalBee.Common.Paging.SliceResponse;
import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceMapper {
    public SliceResponse<Device> toDto (Slice<Device> devices)
    {
        return SliceResponse.<Device>builder()
                .content(devices.getContent())
                .hasNext(devices.hasNext())
                .build();
    }

}
