package Matdol.SmartGazalBee.DeviceComparison.Service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class CrwalServiceImpl implements CrwalService{
    @Override
    public boolean crwaling() {

        try {

            // 자원 폴더에서 Python 스크립트를 로드
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("crawling/main.py");

            if (resource == null) {
                System.err.println("Python 스크립트를 찾을 수 없습니다!");
                return false;
            }

            // URL을 파일 경로로 변환
            String scriptPath = Paths.get(resource.toURI()).toString();

            // Python 스크립트를 실행할 명령어 정의
            String[] command = {"python", scriptPath};

            // ProcessBuilder 생성
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // 프로세스 시작
            Process process = processBuilder.start();

            // 스크립트의 출력값을 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 스크립트의 에러 출력값을 읽기
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            // 프로세스가 완료될 때까지 대기하고 종료 코드 가져오기
            int exitCode = process.waitFor();
            System.out.println("종료 코드: " + exitCode);

            // 스크립트가 성공적으로 실행되었는지 여부 반환
            return exitCode == 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

