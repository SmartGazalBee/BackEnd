package Matdol.SmartGazalBee.User.Domain;

import Matdol.SmartGazalBee.TBoard.Domain.TComment;
import Matdol.SmartGazalBee.User.Domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("SELLER")
@PrimaryKeyJoinColumn(name = "seller_id")
public class Seller extends User {

    private int declaration; //누적 신고횟수

    private String sellerName;

    private String email;

    private String RRN; //Resident Registration Number, 주민등록번호

    private String EID; //Employer ID number, 사업자등록번호

    private String loginId;

    private String loginPw;

    //양방향 관계 설정, seller 삭제 시 cascade되도록 설정
    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<TComment> tComments = new ArrayList<>();

    //양방향 관계 설정, Chatting 생성 시 호출하여 연관 관계 설정
    public void addTComment(TComment tComment) {
        tComments.add(tComment);
        tComment.setSeller(this);
    }

    protected Seller() {}
}
