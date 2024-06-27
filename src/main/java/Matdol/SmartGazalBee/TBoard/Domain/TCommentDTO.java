package Matdol.SmartGazalBee.TBoard.Domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class TCommentDTO {

    private Long id;
    private Long tBoardId;
    //private Long sellerId;
    private String commentContent;
    private String deviceName;
    private int totalPayment; //총납부금
    private String telecom; //통신사
    private Long liker; //좋아요 개수

}