package Matdol.SmartGazalBee.Chatting.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomsDTO {

    private Long id;
    private String nickname;

    public RoomsDTO(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
