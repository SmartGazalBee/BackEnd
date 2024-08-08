package Matdol.SmartGazalBee.DeviceComparison.Domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_name") //,unique = true
    private String deviceName; // 제품명
    private String color; //색상
    @Column(name = "storage_capacity")
    private String storageCapacity; // 용량 메모리, 용량 따로 빼기
    private String weight; // 무게
    private String width; // 가로
    private String length; // 세로
    private String height; // 두깨, 높이
    private String display; // 디스플레이 크기
    private String diagnoal; // 대각선
    private String resolution; // 해상도
    private String chip; // 프로세서
    @Column(name = "release_date")
    private String releaseDate; // 출시일
    private String images;

}
