package Matdol.SmartGazalBee.DeviceComparison.Dto;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

@Builder
@Getter
public class DeviceDto {
    private Device device;
    private File image;
}
