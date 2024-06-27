package Matdol.SmartGazalBee.TBoard.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.TBoard.Domain.TBoardDTO;
import Matdol.SmartGazalBee.TBoard.Service.TBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/TBoard")
public class TBoardController {


    /*
    * 서버에서 여러 게시물 조회할 때 일단 다 불러오고 페이징으로 몇개만 보내는 방식이 비효율적->처음부터 페이징에 맞춰서 불러오도록 수정
    * 서버에서 단일 게시물 조회가 필요한지 소통하기*/


    private final TBoardService tBoardService;

    @PostMapping //게시물 저장
    public ResponseEntity<ResponseBody<TBoardDTO>> createTPost(@RequestBody TBoardDTO tBoardDTO) {
        TBoardDTO createdTBoard = tBoardService.create(tBoardDTO);
        return BeeResponse.toResponse(Status.RUN, createdTBoard);
    }

    @PatchMapping //게시물 수정
    public ResponseEntity<ResponseBody<TBoardDTO>> updateTPost(@RequestBody TBoardDTO tBoardDTO,
                                                               @RequestParam Long postId) {
        TBoardDTO updatedTBoard = tBoardService.update(tBoardDTO, postId);
        return BeeResponse.toResponse(Status.RUN, updatedTBoard);
    }

    @DeleteMapping //게시물 삭제
    public ResponseEntity<ResponseBody> removeTPost(@RequestParam Long postId) {
        tBoardService.delete(postId);
        return BeeResponse.toResponse(Status.RUN);
    }

    @GetMapping //전체 게시물 조회
    public ResponseEntity<ResponseBody<Page<TBoardDTO>>> getEntire(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TBoardDTO> tBoardPage = tBoardService.findAll(pageable);
        return BeeResponse.toResponse(Status.RUN, tBoardPage);
    }

    @GetMapping("/my/{purchaserId}") //내 게시물 조회 -->테스트 못해봄, 인증/인가 구현 후 코드 추가해서 테스트해보기
    public ResponseEntity<ResponseBody<Page<TBoardDTO>>> getTPost(@PathVariable Long purchaserId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TBoardDTO> tBoardPage = tBoardService.findMyPosts(pageable, purchaserId);
        return BeeResponse.toResponse(Status.RUN, tBoardPage);
    }

    @GetMapping("/popular") //인기 게시물 조회 (조건 정하기, 틀만 잡아둔 상태)
    public ResponseEntity<ResponseBody<Page<TBoardDTO>>> getPopular(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TBoardDTO> tBoardPage = tBoardService.findPopularPosts(pageable, 3L);
        return BeeResponse.toResponse(Status.RUN, tBoardPage);
    }
    @GetMapping("/recent") //최근(오늘~3일전까지 내림차순) 게시물 조회, 정렬해서 보내고 json에 date는 포함하지 않음
    public ResponseEntity<ResponseBody<Page<TBoardDTO>>> getRecent(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TBoardDTO> tBoardPage = tBoardService.findRecentPosts(pageable);
        return BeeResponse.toResponse(Status.RUN, tBoardPage);
    }
}