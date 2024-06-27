package Matdol.SmartGazalBee.TBoard.Repository;

import Matdol.SmartGazalBee.TBoard.Domain.TComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCommentRepository extends JpaRepository<TComment, Long> {

    @Query("SELECT t FROM TComment t where t.tBoard.id = :postId")
    List<TComment> findListByPostId(@Param("postId") Long postId);
}
