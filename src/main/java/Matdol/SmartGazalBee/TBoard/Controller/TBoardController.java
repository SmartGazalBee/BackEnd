package Matdol.SmartGazalBee.TBoard.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.TBoard.Service.TBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/TBoard")
public class TBoardController {

    private final TBoardService tBoardService;

    @PostMapping //게시물 저장
    public ResponseEntity<ResponseBody<TBoardDTO>> createTPost(@RequestBody TBoardDTO tBoardDTO) {
        TBoardDTO createdTBoard = tBoardService.create(tBoardDTO);
        return BeeResponse.toResponse(Status.RUN, createdTBoard);
    }

    @PostMapping("/update") //게시물 수정
    public String updateTPost() {
        return "";
    }

    @GetMapping("/remove") //게시물 삭제
    public String removeTPost() {
        return "";
    }

    @GetMapping("") //내 게시물 조회
    public String getTPost() {
        return "";
    }

    @GetMapping("/entire") //전체 게시물 조회
    public String getEntire() {
        return "";
    }

    @GetMapping("/popular") //인기 게시물 조회
    public String getPopular() {
        return "";
    }

    @GetMapping("/recent") //최근 게시물 조회
    public String getRecent() {
        return "";
    }

}