package Matdol.SmartGazalBee.User.Domain;

import Matdol.SmartGazalBee.Chatting.Domain.Chatting;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import Matdol.SmartGazalBee.TBoard.Domain.TComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String name;

    private String loginEmail;

    private String loginPw; //암호화하기

    private int declaration; //누적 신고횟수

    private String fcmToken; //fcmToken

    private UserType userType; //구매자 혹은 판매자

    //양방향 관계 설정, 삭제 시 함께 삭제되도록 cascade 설정
    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.REMOVE)
    private List<Chatting> FromChattings = new ArrayList<>();
    @OneToMany(mappedBy = "toUser", cascade = CascadeType.REMOVE)
    private List<Chatting> ToChattings = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<TComment> tComments = new ArrayList<>();
    @OneToMany(mappedBy = "purchaser", cascade = CascadeType.REMOVE)
    private List<TBoard> tBoards = new ArrayList<>();

    //양방향 관계 설정,
    public void addTBoard(TBoard tBoard) {
        tBoards.add(tBoard);
        tBoard.setPurchaser(this);
    }
    public void addTComment(TComment tComment) {
        tComments.add(tComment);
        tComment.setSeller(this);
    }
    //양방향 관계 설정, Chatting 생성 시 호출하여 연관 관계 설정
    public void addFromChatting(Chatting chatting) {
        FromChattings.add(chatting);
        chatting.setFromUser(this);
    }
    public void addToChatting(Chatting chatting) {
        ToChattings.add(chatting);
        chatting.setToUser(this);
    }

   }