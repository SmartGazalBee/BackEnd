package Matdol.SmartGazalBee.DeviceComparison.Repository;

import org.springframework.stereotype.Repository;

import java.io.File;
@Repository
public class FileRepositoryImpl implements FileRepository{
    @Override
    public File getFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("Error: File not found at path: " + path);
            return null;
        }
        return file;
    }
}
