package Matdol.Domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickName;

    private int declaration; //누적 신고횟수

    private boolean sellerPermission; //판매자 허가 여부


    //양방향 관계 설정, member 삭제 시 함께 삭제되도록 cascade 설정
    @OneToMany(mappedBy = "purchaser", cascade = CascadeType.REMOVE)
    private List<Chatting> purchaserChattings = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<Chatting> sellerChattings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<FBoard> fBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<FBoardComment> fBoardComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<FBoardImg> fBoardImgs = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<TBoard> tBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<TBoardComment> tBoardComments = new ArrayList<>();


    //양방향 관계 설정, Chatting 생성 시 호출하여 연관 관계 설정
    public void addChatting(Chatting chatting) {
        purchaserChattings.add(chatting);
        chatting.setPurchaser(this);
        sellerChattings.add(chatting);
        chatting.setSeller(this);
    }

    public void addFBoard(FBoard fBoard) {
        fBoards.add(fBoard);
        fBoard.setMember(this);
    }

    public void addFBoardComment(FBoardComment fBoardComment) {
        fBoardComments.add(fBoardComment);
        fBoardComment.setMember(this);
    }

    public void addFBoardImg(FBoardImg fBoardImg) {
        fBoardImgs.add(fBoardImg);
        fBoardImg.setMember(this);
    }

    public void addTBoard(TBoard tBoard) {
        tBoards.add(tBoard);
        tBoard.setMember(this);
    }

    public void addTBoardComment(TBoardComment tBoardComment) {
        tBoardComments.add(tBoardComment);
        tBoardComment.setMember(this);
    }

    protected Member(){} //JPA 규약 상 파라미터 없는 protected 생성자 정의 필요
}
