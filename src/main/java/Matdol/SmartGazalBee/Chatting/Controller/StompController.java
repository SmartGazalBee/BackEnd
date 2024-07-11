package Matdol.SmartGazalBee.Chatting.Controller;

import Matdol.SmartGazalBee.Chatting.Domain.ChattingDTO;
import Matdol.SmartGazalBee.Chatting.Service.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
@Log4j2
public class StompController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final ChattingService chattingService;

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChattingDTO chattingDTO){
        log.warn("채팅DTO: [] {}, [] {}",chattingDTO.getToId(), chattingDTO.getFromId());
        String roomId = chattingService.getRoomId(chattingDTO.getFromId(), chattingDTO.getToId());
        chattingDTO.setMessage("채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + roomId, chattingDTO);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChattingDTO chattingDTO){
        String roomId = chattingService.getRoomId(chattingDTO.getFromId(), chattingDTO.getToId());
        chattingService.create(chattingDTO);
        template.convertAndSend("/sub/chat/room/" + roomId, chattingDTO);
    }


}