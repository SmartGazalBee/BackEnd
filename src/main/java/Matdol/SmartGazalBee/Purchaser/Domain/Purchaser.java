package Matdol.SmartGazalBee.Purchaser.Domain;

import Matdol.SmartGazalBee.Chatting.Domain.Chatting;
import Matdol.SmartGazalBee.FBoard.Domain.FBoard;
import Matdol.SmartGazalBee.FBoard.Domain.FBoardImg;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Purchaser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaser_id")
    private Long id;

    private String nickName;

    private int declaration; //누적 신고횟수

    private String memberName;

    private String email;

    //양방향 관계 설정, purchaser 삭제 시 함께 삭제되도록 cascade 설정
    @OneToMany(mappedBy = "purchaser", cascade = CascadeType.REMOVE)
    private List<Chatting> purchaserChattings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<FBoard> fBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<FBoardImg> fBoardImgs = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<TBoard> tBoards = new ArrayList<>();


    //양방향 관계 설정, Chatting 생성 시 호출하여 연관 관계 설정
    public void addChatting(Chatting chatting) {
        purchaserChattings.add(chatting);
        chatting.setPurchaser(this);
    }

    public void addFBoard(FBoard fBoard) {
        fBoards.add(fBoard);
        fBoard.setPurchaser(this);
    }

    public void addFBoardImg(FBoardImg fBoardImg) {
        fBoardImgs.add(fBoardImg);
        fBoardImg.setPurchaser(this);
    }

    public void addTBoard(TBoard tBoard) {
        tBoards.add(tBoard);
        tBoard.setPurchaser(this);
    }

    protected Purchaser() {}
}
