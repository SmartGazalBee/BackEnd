package Matdol.SmartGazalBee.TBoard.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TBoard")
public class TBoardController {

    @PostMapping("/new") //게시물 저장
    public String newTPost() {
        return "";
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