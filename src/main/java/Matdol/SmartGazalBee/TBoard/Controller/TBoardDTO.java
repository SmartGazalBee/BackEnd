package Matdol.SmartGazalBee.TBoard.Controller;

import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TBoardDTO {
    private Long id;
    private Long purchaserId = 1L; //테스트용 임의값, 인증/인가 구현 후 초기화값 제거하기
    private String postTitle;
    private String postDevice;
    private int postPrice;
    private String postDescription;
    private Long postHits = 0L;


    public TBoardDTO(Long id, /*Long purchaserId,*/String postTitle, String postDevice, int postPrice, String postDescription, Long postHits) {
        this.id = id;
        //this.purchaserId = purchaserId;
        this.postTitle = postTitle;
        this.postDevice = postDevice;
        this.postPrice = postPrice;
        this.postDescription = postDescription;
        this.postHits = postHits;

    }

    public static TBoardDTO fromEntity(TBoard tBoard) {
        return new TBoardDTO(
                tBoard.getId(),
                //tBoard.getPurchaser().getId(),
                tBoard.getPostTitle(),
                tBoard.getPostDevice(),
                tBoard.getPostPrice(),
                tBoard.getPostDescription(),
                tBoard.getPostHits()
        );
    }

    public TBoard toEntity() {
        return new TBoard(this.getPurchaserId(),
                this.getPostTitle(),
                this.getPostDevice(),
                this.getPostPrice(),
                this.getPostDescription(),
                this.getPostHits());
    };
}