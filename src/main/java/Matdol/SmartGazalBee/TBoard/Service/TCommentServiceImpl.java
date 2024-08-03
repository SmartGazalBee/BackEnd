package Matdol.SmartGazalBee.TBoard.Service;

import Matdol.SmartGazalBee.TBoard.Domain.TCommentDTO;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import Matdol.SmartGazalBee.TBoard.Domain.TComment;
import Matdol.SmartGazalBee.TBoard.Repository.TBoardRepository;
import Matdol.SmartGazalBee.TBoard.Repository.TCommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TCommentServiceImpl implements TCommentService{

    private final TBoardRepository tBoardRepository;
    private final TCommentRepository tCommentRepository;
    //private final SellerRepository sellerRepository;

    @Override
    @Transactional
    public TCommentDTO create(Long postId, TCommentDTO tCommentDTO) {
        TComment tComment = toEntity(tCommentDTO);
        TBoard tBoard = tBoardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TComment ID"));
        tComment.setTBoard(tBoard);
        tComment = tCommentRepository.save(tComment);
        return fromEntity(tComment);
    }

    @Override
    @Transactional
    public void delete(Long commentId) {
        TComment tComment = tCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TComment ID"));
        tCommentRepository.delete(tComment);
    }

    @Override
    public List<TCommentDTO> findListByPostId(Long postId) {
        List<TComment> comments = tCommentRepository.findListByPostId(postId);
        return comments.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }


    private TComment toEntity(TCommentDTO tCommentDTO) {
        return new TComment(
                tBoardRepository.findById(tCommentDTO.getTBoardId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid TBoard ID")),
                //sellerRepository.findById(tCommentDTO.getSellerId())
                //.orElseThrow(() -> new IllegalArgumentException("Invalid TBoard ID")),
                tCommentDTO.getCommentContent(),
                tCommentDTO.getDeviceName(),
                tCommentDTO.getTotalPayment(),
                tCommentDTO.getTelecom(),
                tCommentDTO.getLiker()
        );
    }

    private TCommentDTO fromEntity(TComment tComment) {
        return new TCommentDTO(
                tComment.getId(),
                tComment.getTBoard().getId(),
                //tComment.getSeller().getId(),
                tComment.getCommentContent(),
                tComment.getDeviceName(),
                tComment.getTotalPayment(),
                tComment.getTelecom(),
                tComment.getLiker()
        );
    }
}
