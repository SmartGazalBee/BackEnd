package Matdol.SmartGazalBee.TBoard.Service;

import Matdol.SmartGazalBee.TBoard.Controller.TBoardDTO;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import Matdol.SmartGazalBee.TBoard.Repository.TBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

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

    @Transactional
    public TBoardDTO update(TBoardDTO tBoardDTO, Long postId) {
        TBoard requstTBoard = tBoardDTO.toEntity();
        TBoard serverTBoard = tBoardRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("TBoard not found with id: " + postId));

        serverTBoard.updateTBoard(requstTBoard.getPostTitle(), requstTBoard.getPostDevice(), requstTBoard.getPostPrice(), requstTBoard.getPostDescription(), requstTBoard.getPostHits());
        serverTBoard = tBoardRepository.save(serverTBoard);

        return TBoardDTO.fromEntity(serverTBoard);
    }

    @Transactional
    public void delete(Long postId) {
        TBoard tBoard = tBoardRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("TBoard not found with id: " + postId));
        tBoardRepository.delete(tBoard);
    }

    public Page<TBoardDTO> findAll(Pageable pageable){
        Page<TBoard> tBoardPage = tBoardRepository.findAll(pageable);
        return tBoardPage.map(TBoardDTO::fromEntity);
    }

    public Page<TBoardDTO> findMyPosts(Pageable pageable, Long purchaserId){
        Page<TBoard> tBoardPage = tBoardRepository.findMyPosts(pageable, purchaserId);
        return tBoardPage.map(TBoardDTO::fromEntity);
    }

    public Page<TBoardDTO> findPopularPosts(Pageable pageable, Long standard){
        Page<TBoard> tBoardPage = tBoardRepository.findPopularPosts(pageable, standard);
        return tBoardPage.map(TBoardDTO::fromEntity);
    }

    public Page<TBoardDTO> findRecentPosts(Pageable pageable){
        LocalDate standard = LocalDate.now().minusDays(3);
        Page<TBoard> tBoardPage = tBoardRepository.findRecentPosts(pageable, standard);
        return tBoardPage.map(TBoardDTO::fromEntity);
    }
}
