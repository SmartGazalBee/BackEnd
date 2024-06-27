package Matdol.SmartGazalBee.User.Domain;

import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import Matdol.SmartGazalBee.User.Domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("PURCHASER")
@PrimaryKeyJoinColumn(name = "purchaser_id")
public class Purchaser extends User {

    private int declaration; //누적 신고횟수

    private String purchaserName;

    private String email;

    //양방향 관계 설정, purchaser 삭제 시 함께 삭제되도록 cascade 설정
    @OneToMany(mappedBy = "purchaser", cascade = CascadeType.REMOVE)
    private List<TBoard> tBoards = new ArrayList<>();


    //양방향 관계 설정, Chatting 생성 시 호출하여 연관 관계 설정
    public void addTBoard(TBoard tBoard) {
        tBoards.add(tBoard);
        tBoard.setPurchaser(this);
    }

    protected Purchaser() {}
}
