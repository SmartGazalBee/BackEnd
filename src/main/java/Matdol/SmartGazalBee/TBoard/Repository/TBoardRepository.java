package Matdol.SmartGazalBee.TBoard.Repository;

import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface TBoardRepository extends JpaRepository<TBoard, Long> {

    @Query("SELECT t FROM TBoard t where t.purchaser.id = :purchaserId")
    public Page<TBoard> findMyPosts(Pageable pageable, @Param("purchaserId") Long purchaserId);

    @Query("SELECT t FROM TBoard t where t.postHits >= :standard")
    public Page<TBoard> findPopularPosts(Pageable pageable, @Param("standard") Long standard);

    @Query(value = "SELECT t FROM TBoard t WHERE t.postDate >= :standard ORDER BY t.postDate DESC")
    public Page<TBoard> findRecentPosts(Pageable pageable, @Param("standard") LocalDate standard);
}