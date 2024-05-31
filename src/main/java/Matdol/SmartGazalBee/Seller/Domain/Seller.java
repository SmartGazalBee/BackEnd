package Matdol.SmartGazalBee.Seller.Domain;

import Matdol.SmartGazalBee.Chatting.Domain.Chatting;
import Matdol.SmartGazalBee.FBoard.Domain.FBoardComment;
import Matdol.SmartGazalBee.TBoard.Domain.TBoardComment;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickName;

    private int declaration; //누적 신고횟수

    private String memberName;

    private String email;

    private String RRN; //Resident Registration Number, 주민등록번호

    private String EID; //Employer ID number, 사업자등록번호

    private String loginId;

    private String loginPw;

    //양방향 관계 설정, seller 삭제 시 cascade되도록 설정
    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<Chatting> sellerChattings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<FBoardComment> fBoardComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<TBoardComment> tBoardComments = new ArrayList<>();

    //양방향 관계 설정, Chatting 생성 시 호출하여 연관 관계 설정
    public void addChatting(Chatting chatting) {
        sellerChattings.add(chatting);
        chatting.setSeller(this);
    }

    public void addFBoardComment(FBoardComment fBoardComment) {
        fBoardComments.add(fBoardComment);
        fBoardComment.setSeller(this);
    }

    public void addTBoardComment(TBoardComment tBoardComment) {
        tBoardComments.add(tBoardComment);
        tBoardComment.setSeller(this);
    }

    protected Seller() {}
}
