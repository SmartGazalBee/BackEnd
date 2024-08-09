package Matdol.SmartGazalBee.TBoard.Service;

import Matdol.SmartGazalBee.TBoard.Domain.TBoardDTO;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import Matdol.SmartGazalBee.TBoard.Repository.TBoardRepository;
import Matdol.SmartGazalBee.User.Domain.User;
import Matdol.SmartGazalBee.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class TBoardServiceImpl implements TBoardService {

    private final TBoardRepository tBoardRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public TBoardDTO create(TBoardDTO tBoardDTO) {
        TBoard tBoard = toEntity(tBoardDTO);
        tBoard = tBoardRepository.save(tBoard);
        return fromEntity(tBoard);
    }

    @Override
    @Transactional
    public TBoardDTO update(TBoardDTO tBoardDTO, Long postId) {
        TBoard tBoard = tBoardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TBoard ID"));

        tBoard.updateTBoard(tBoardDTO.getPostTitle(), tBoardDTO.getPostDevice(), tBoardDTO.getPostPrice(),
                tBoardDTO.getPostDescription(), tBoardDTO.getPostHits());
        tBoard = tBoardRepository.save(tBoard);

        return fromEntity(tBoard);
    }

    @Override
    @Transactional
    public void delete(Long postId) {
        TBoard tBoard = tBoardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TBoard ID"));
        tBoardRepository.delete(tBoard);
    }

    @Override
    public Page<TBoardDTO> findAll(Pageable pageable) {
        Page<TBoard> tBoardPage = tBoardRepository.findAll(pageable);
        return tBoardPage.map(this::fromEntity);
    }

    @Override
    public Page<TBoardDTO> findMyPosts(Pageable pageable, Long purchaserId) {
        Page<TBoard> tBoardPage = tBoardRepository.findMyPosts(pageable, purchaserId);
        return tBoardPage.map(this::fromEntity);
    }

    @Override
    public Page<TBoardDTO> findPopularPosts(Pageable pageable, Long standard) {
        Page<TBoard> tBoardPage = tBoardRepository.findPopularPosts(pageable, standard);
        return tBoardPage.map(this::fromEntity);
    }

    @Override
    public Page<TBoardDTO> findRecentPosts(Pageable pageable) {
        LocalDate standard = LocalDate.now().minusDays(3);
        Page<TBoard> tBoardPage = tBoardRepository.findRecentPosts(pageable, standard);
        return tBoardPage.map(this::fromEntity);
    }

    private TBoard toEntity(TBoardDTO tBoardDTO) {
        return new TBoard(
                userRepository.findById(tBoardDTO.getPurchaserId())
                 .orElseThrow(() -> new IllegalArgumentException("Invalid purchaser ID")),
                tBoardDTO.getPostTitle(),
                tBoardDTO.getPostDevice(),
                tBoardDTO.getPostPrice(),
                tBoardDTO.getPostDescription(),
                tBoardDTO.getPostHits()
        );
    }

    private TBoardDTO fromEntity(TBoard tBoard) {
        return new TBoardDTO(
                tBoard.getId(),
                tBoard.getPurchaser().getId(),
                tBoard.getPostTitle(),
                tBoard.getPostDevice(),
                tBoard.getPostPrice(),
                tBoard.getPostDescription(),
                tBoard.getPostHits()
        );
    }
}