package Matdol.SmartGazalBee.Chatting.Controller;

import Matdol.SmartGazalBee.Chatting.Domain.ChattingDTO;
import Matdol.SmartGazalBee.Chatting.Domain.RoomsDTO;
import Matdol.SmartGazalBee.Chatting.Service.ChattingService;
import Matdol.SmartGazalBee.User.Domain.User;
import Matdol.SmartGazalBee.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class ChattingController {

    private final ChattingService chattingService;
    private final UserRepository userRepository;

    //채팅방 목록 조회
    @GetMapping("/rooms")
    public String rooms(Model model,
                        @RequestParam Long myId){

        //myId와 채팅 내역이 있는 상대방 목록 조회
        List<RoomsDTO> roomsDTOList = chattingService.findThem(myId);

        model.addAttribute("myId",myId);
        model.addAttribute("roomsDTOList",roomsDTOList);
        log.info("# All Chat Rooms");

        return "chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room/{yourId}")
    public String getRoom(@PathVariable("yourId") Long yourId,
                          @RequestParam Long myId,
                          @RequestParam(required = false) String yourNickname,
                          Model model){

        //대화내역 불러오기 //api에서 멈출거면 이걸 json형식으로 리턴하기
        List<ChattingDTO> chattingDTOList = chattingService.findDetails(yourId,myId);

        //대화내역 프론트코드에 뿌려주기
        model.addAttribute("chattingDTOList",chattingDTOList);

        //나중에 수정하기, 불필요한 db접근
        User user = userRepository.findById(myId).orElse(null);
        model.addAttribute("username", user.getNickname());


        //임시 코드, 리팩토링하기
        model.addAttribute("myId",myId);
        model.addAttribute("yourId",yourId);
        model.addAttribute("yourNickname",yourNickname);

        //roomId 보내기
        model.addAttribute("roomId",chattingService.getRoomId(myId,yourId));

        return "chat/room";
    }

}