package Matdol.SmartGazalBee.TBoard.Domain;

import Matdol.SmartGazalBee.Seller.Domain.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
public class TBoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_comment_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_post_id")
    @JsonIgnore
    private TBoard tBoard;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller;

    private String commentContent;

    private LocalDate commentDate;

    private String deviceName;

    private int totalPayment; //총납부금

    private String telecom; //통신사

    private Long liker; //좋아요 개수

    protected TBoardComment() {}

}
