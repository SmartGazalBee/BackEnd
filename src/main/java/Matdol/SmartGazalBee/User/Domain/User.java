package Matdol.SmartGazalBee.User.Domain;

import Matdol.SmartGazalBee.Chatting.Domain.Chatting;
import Matdol.SmartGazalBee.FBoard.Domain.FBoard;
import Matdol.SmartGazalBee.FBoard.Domain.FBoardImg;
import Matdol.SmartGazalBee.FBoard.Domain.FComment;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    //양방향 관계 설정, purchaser 삭제 시 함께 삭제되도록 cascade 설정
    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.REMOVE)
    private List<Chatting> FromChattings = new ArrayList<>();
    @OneToMany(mappedBy = "toUser", cascade = CascadeType.REMOVE)
    private List<Chatting> ToChattings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<FBoard> fBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<FComment> fComments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<FBoardImg> fBoardImgs = new ArrayList<>();


    //양방향 관계 설정, Chatting 생성 시 호출하여 연관 관계 설정
    public void addFromChatting(Chatting chatting) {
        FromChattings.add(chatting);
        chatting.setFromUser(this);
    }
    public void addToChatting(Chatting chatting) {
        ToChattings.add(chatting);
        chatting.setToUser(this);
    }

    public void addFBoard(FBoard fBoard) {
        fBoards.add(fBoard);
        fBoard.setUser(this);
    }

    public void addFBoardComment(FComment fComment) {
        fComments.add(fComment);
        fComment.setUser(this);
    }

    public void addFBoardImg(FBoardImg fBoardImg) {
        fBoardImgs.add(fBoardImg);
        fBoardImg.setUser(this);
    }

   }