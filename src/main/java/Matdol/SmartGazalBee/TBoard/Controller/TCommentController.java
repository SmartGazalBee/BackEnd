package Matdol.SmartGazalBee.TBoard.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.TBoard.Domain.TCommentDTO;
import Matdol.SmartGazalBee.TBoard.Service.TCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/TComment")
public class TCommentController {

    private final TCommentService tCommentService;

    @PostMapping //댓글 저장
    public ResponseEntity<ResponseBody<TCommentDTO>> createTComment(@RequestParam Long postId,
                                                                    @RequestBody TCommentDTO tCommentDTO) {
        TCommentDTO createdTComment = tCommentService.create(postId, tCommentDTO);
        return BeeResponse.toResponse(Status.RUN, createdTComment);
    }

    @DeleteMapping //댓글 삭제
    public ResponseEntity<ResponseBody> deleteTComment(@RequestParam Long commentId) {
        tCommentService.delete(commentId);
        return BeeResponse.toResponse(Status.RUN);
    }

    //댓글 수정 기능은 제공 안함

    @GetMapping//post에 해당하는 댓글 조회
    public ResponseEntity<ResponseBody<List<TCommentDTO>>> getTComment(@RequestParam Long postId) {
        List<TCommentDTO> tCommentDTOList = tCommentService.findListByPostId(postId);
        return BeeResponse.toResponse(Status.RUN, tCommentDTOList);
    }

}
