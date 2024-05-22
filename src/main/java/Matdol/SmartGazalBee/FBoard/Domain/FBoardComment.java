package Matdol.SmartGazalBee.FBoard.Domain;

import Matdol.SmartGazalBee.Auth.Domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
public class FBoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_comment_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_post_id")
    @JsonIgnore
    private FBoard fBoard;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    private String commentContent;

    private Date commentDate;

    protected FBoardComment() {}
}
