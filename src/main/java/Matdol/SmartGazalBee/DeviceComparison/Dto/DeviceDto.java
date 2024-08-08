package Matdol.SmartGazalBee.DeviceComparison.Dto;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceDto {
    private Device device;
    @JsonProperty
    private byte[] image;
}
