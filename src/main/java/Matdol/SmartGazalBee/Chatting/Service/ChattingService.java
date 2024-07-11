package Matdol.SmartGazalBee.Chatting.Service;

import Matdol.SmartGazalBee.Chatting.Domain.ChattingDTO;
import Matdol.SmartGazalBee.Chatting.Domain.RoomsDTO;

import java.util.List;

public interface ChattingService {

    public ChattingDTO create(ChattingDTO chattingDTO);

    public List<ChattingDTO> findDetails(Long sellerId, Long purchaserId);

    public List<RoomsDTO> findThem(Long myId);

    public String getRoomId(Long fromId, Long toId);
}
