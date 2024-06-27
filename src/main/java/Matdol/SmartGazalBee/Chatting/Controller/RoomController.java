package Matdol.SmartGazalBee.Chatting.Controller;

import Matdol.SmartGazalBee.Chatting.Repository.ChatRoomRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    private final ChatRoomRepository chatRoomRepository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public String rooms(Model model){

        log.info("# All Chat Rooms");

        model.addAttribute("list",chatRoomRepository.findAllRooms());
        return "chat/rooms";
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){

        log.info("# Create Chat Room , name: " + name);
        rttr.addFlashAttribute("roomName", chatRoomRepository.createChatRoomDTO(name));
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public String getRoom(String roomId, Model model, HttpSession session){

        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", chatRoomRepository.findRoomById(roomId));

        // 테스트용 username 추가
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "testUser" + (int)(Math.random() * 100);
            session.setAttribute("username", username);
        }
        model.addAttribute("username", username);

        return "chat/room";
    }

}