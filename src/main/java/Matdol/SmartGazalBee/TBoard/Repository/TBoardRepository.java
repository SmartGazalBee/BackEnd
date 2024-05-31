package Matdol.SmartGazalBee.TBoard.Repository;

import Matdol.SmartGazalBee.TBoard.Controller.TBoardDTO;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TBoardRepository extends JpaRepository<TBoard, Long> {

}
