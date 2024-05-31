package Matdol.SmartGazalBee.TBoard.Service;

import Matdol.SmartGazalBee.TBoard.Controller.TBoardDTO;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import Matdol.SmartGazalBee.TBoard.Repository.TBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TBoardServiceImpl implements TBoardService{

    private final TBoardRepository tBoardRepository;

    @Transactional
    public TBoardDTO create(TBoardDTO tBoardDTO){
        TBoard tBoard = tBoardDTO.toEntity();
        tBoard = tBoardRepository.save(tBoard); //spring data jpa: 기본키 자동 증가 후 tBoard에 자동 할당

        return TBoardDTO.fromEntity(tBoard);
    }
}
