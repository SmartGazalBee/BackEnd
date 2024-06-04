package Matdol.SmartGazalBee.Chatting.Domain;

import Matdol.SmartGazalBee.Purchaser.Domain.Purchaser;
import Matdol.SmartGazalBee.Seller.Domain.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private Purchaser purchaser;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller;

    private String chatContent;

    private LocalDate chatCreatedTime;

    protected Chatting() {}
}
