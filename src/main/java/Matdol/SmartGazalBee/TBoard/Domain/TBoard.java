package Matdol.SmartGazalBee.TBoard.Domain;

import Matdol.SmartGazalBee.Purchaser.Domain.Purchaser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class TBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_post_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_id")
    @JsonIgnore
    private Purchaser purchaser;

    private String postTitle;

    private String postDevice;

    private int postPrice;

    private String postDescription;

    //양방향 관계 설정, tBoard 삭제 시 함께 삭제되도록 cascade 설정
    @OneToMany(mappedBy = "tBoard", cascade = CascadeType.REMOVE)
    private List<TBoardComment> tBoardComments = new ArrayList<>();

    //양방향 관계 설정, tBoardComment 생성 시 호출하여 연관 관계 설정
    public void addTBoardComment(TBoardComment tBoardComment){
        tBoardComments.add(tBoardComment);
        tBoardComment.setTBoard(this);
    }

    public TBoard() {}

    public TBoard(Long purchaserId, String postTitle, String postDevice, int postPrice, String postDescription) {
        //this.purchaser = memberRepository.findById(purchaserId);
        this.postTitle = postTitle;
        this.postDevice = postDevice;
        this.postPrice = postPrice;
        this.postDescription = postDescription;

        //this.purchaser.addTBoard(this);
    }

    public void updateTBoard(String postTitle, String postDevice, int postPrice, String postDescription) {
        this.postTitle = postTitle;
        this.postDevice = postDevice;
        this.postPrice = postPrice;
        this.postDescription = postDescription;
    }
}


