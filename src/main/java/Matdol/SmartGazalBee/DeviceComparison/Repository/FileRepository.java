package Matdol.SmartGazalBee.DeviceComparison.Repository;

import org.springframework.stereotype.Repository;

import java.io.File;

public interface FileRepository {
    File getFile(String path);
}
