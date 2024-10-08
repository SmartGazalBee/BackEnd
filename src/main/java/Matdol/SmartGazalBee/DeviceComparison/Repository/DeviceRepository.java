package Matdol.SmartGazalBee.DeviceComparison.Repository;

import Matdol.SmartGazalBee.DeviceComparison.Domain.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
//    @Query("SELECT d FROM Device d ORDER BY d.id DESC")
    Slice<Device> findAllByOrderByIdDesc(Pageable pageable);
//    @Query("SELECT d FROM Device d WHERE d.id < :id ORDER BY d.id DESC")
    Slice<Device> findByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
    Boolean existsByIdLessThan(Long id);

    @Query("SELECT d FROM Device d WHERE LOWER(d.deviceName) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY d.id DESC")
    Slice<Device> findByNameContainingOrderByIdDesc(@Param("name") String name, Pageable pageable);

    @Query("SELECT d FROM Device d WHERE d.id < :id AND LOWER(d.deviceName) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY d.id DESC")
    Slice<Device> findByIdLessThanAndNameContainingOrderByIdDesc(@Param("id") Long id, @Param("name") String name, Pageable pageable);
}
