package Matdol.SmartGazalBee.Chatting.Repository;

import Matdol.SmartGazalBee.Chatting.Domain.Chatting;
import Matdol.SmartGazalBee.TBoard.Domain.TComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRepository extends JpaRepository<Chatting, Long> {

    @Query("SELECT c FROM Chatting c where c.fromUser.id = :yourId and c.toUser.id = :myId " +
            "or c.fromUser.id = :myId and c.toUser.id = :yourId")
    List<Chatting> findDetails(@Param("yourId") Long yourId, @Param("myId") Long myId);

    @Query("SELECT DISTINCT toUser.id FROM Chatting c JOIN c.toUser toUser WHERE c.fromUser.id = :myId " +
            "UNION " +
            "SELECT DISTINCT fromUser.id FROM Chatting c JOIN c.fromUser fromUser WHERE c.toUser.id = :myId")
    List<Long> findThem(@Param("myId") Long myId);
}