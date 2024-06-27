package Matdol.SmartGazalBee.Chatting.Domain;

import Matdol.SmartGazalBee.User.Domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    @JsonIgnore
    private User fromUser;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "To_id")
    @JsonIgnore
    private User toUser;

    private String chatContent;

    private LocalDate chatTime;

    protected Chatting() {}
}
