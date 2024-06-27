package Matdol.SmartGazalBee.FBoard.Domain;

import Matdol.SmartGazalBee.User.Domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class FBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_post_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String postTitle;

    private String postContent;

    private LocalDate postDate;

    private Long postHits; //조회수

    //양방향 관계 설정, fBoard 삭제 시 함께 삭제되도록 cascade 설정
    @OneToMany(mappedBy = "fBoard", cascade = CascadeType.REMOVE)
    private List<FComment> fComments = new ArrayList<>();

    @OneToMany(mappedBy = "fBoard", cascade = CascadeType.REMOVE)
    private List<FBoardImg> fBoardImgs = new ArrayList<>();


    //양방향 관계 설정, FBoardComment 생성 시 호출하여 연관 관계 설정
    public void addFComment(FComment fComment){
        fComments.add(fComment);
        fComment.setFBoard(this);
    }
    public void addFBoardImg(FBoardImg fBoardImg){
        fBoardImgs.add(fBoardImg);
        fBoardImg.setFBoard(this);
    }

    protected FBoard() {}
}
