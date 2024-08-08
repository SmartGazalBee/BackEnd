package Matdol.SmartGazalBee.DeviceComparison.Repository;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class FileRepositoryImpl implements FileRepository{
    @Override
    public byte[] getFile(String path) {

        try {
            ClassPathResource resource = new ClassPathResource(path);
            // InputStream을 사용하여 파일을 읽음
            try (InputStream inputStream = resource.getInputStream()) {
                // InputStream을 바이트 배열로 변환
                return IOUtils.toByteArray(inputStream);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
