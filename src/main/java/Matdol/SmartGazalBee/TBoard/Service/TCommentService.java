package Matdol.SmartGazalBee.TBoard.Service;

import Matdol.SmartGazalBee.TBoard.Domain.TCommentDTO;

import java.util.List;

public interface TCommentService {

    public List<TCommentDTO> findListByPostId(Long postId);

    public TCommentDTO create(Long postId, TCommentDTO tCommentDTO);

    public void delete(Long commentId);

}
