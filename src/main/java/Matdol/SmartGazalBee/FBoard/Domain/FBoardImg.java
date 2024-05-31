package Matdol.SmartGazalBee.FBoard.Domain;

import Matdol.SmartGazalBee.Purchaser.Domain.Purchaser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class FBoardImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_img_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_post_id")
    @JsonIgnore
    private FBoard fBoard;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_id")
    @JsonIgnore
    private Purchaser purchaser;

    private String imgPath;

    protected FBoardImg() {}
}
