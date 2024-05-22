package Matdol.SmartGazalBee.Chatting.Domain;

import Matdol.SmartGazalBee.Auth.Domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_id")
    @JsonIgnore
    private Member purchaser;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Member seller;

    private String chatContent;

    private Date chatCreatedTime;

    protected Chatting() {}
}
