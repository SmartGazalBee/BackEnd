package Matdol.SmartGazalBee.Chatting.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class ChattingDTO {
    private Long fromId;
    private Long toId;
    private String message;
    private LocalDateTime chatTime;
}
