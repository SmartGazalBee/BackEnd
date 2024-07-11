package Matdol.SmartGazalBee.Chatting.Service;

import Matdol.SmartGazalBee.Chatting.Domain.Chatting;
import Matdol.SmartGazalBee.Chatting.Domain.ChattingDTO;
import Matdol.SmartGazalBee.Chatting.Domain.RoomsDTO;
import Matdol.SmartGazalBee.Chatting.Repository.ChattingRepository;
import Matdol.SmartGazalBee.User.Domain.User;
import Matdol.SmartGazalBee.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChattingServiceImpl implements ChattingService{

    private final ChattingRepository chattingRepository;
    private final UserRepository userRepository;


    //채팅 내역 저장하기
    @Override
    @Transactional
    public ChattingDTO create(ChattingDTO chattingDTO) {
        Chatting chatting = toEntity(chattingDTO);
        chatting = chattingRepository.save(chatting);
        return fromEntity(chatting);
    }

    //이어서 채팅하기 위한, 과거 채팅 내역 가져오기
    @Override
    public List<ChattingDTO> findDetails(Long yourId, Long myId){
        List<Chatting> comments = chattingRepository.findDetails(yourId,myId);
        return comments.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    //채팅방 목록에 필요한 상대방 유저 정보 가져오기
    @Override
    public List<RoomsDTO> findThem(Long myId){
        List<Long> themIds = chattingRepository.findThem(myId);
        List<RoomsDTO> roomsDTOList = new ArrayList<>();

        for (Long themId : themIds) {
            User user = userRepository.findById(themId).orElse(null);
            if (user != null) {
                RoomsDTO roomsDTO = new RoomsDTO(user.getId(), user.getNickname());
                roomsDTOList.add(roomsDTO);
            }
        }

        return roomsDTOList;
    }

    private Chatting toEntity(ChattingDTO chattingDTO) {
        return new Chatting(
                userRepository.findById(chattingDTO.getFromId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid purchaser ID")),
                userRepository.findById(chattingDTO.getToId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid purchaser ID")),
                chattingDTO.getMessage());
    }

    private ChattingDTO fromEntity(Chatting chatting) {
        return new ChattingDTO(
                chatting.getFromUser().getId(),
                chatting.getToUser().getId(),
                chatting.getMessage(),
                chatting.getChatTime());
    }

    //채팅방 고유 식별 id 생성
    public String getRoomId(Long fromId, Long toId) {
        // ID들을 문자열 배열로 변환하고 정렬
        Long[] ids = {fromId, toId};
        Arrays.sort(ids);

        return ids[0] + "A" + ids[1];
    }
}
