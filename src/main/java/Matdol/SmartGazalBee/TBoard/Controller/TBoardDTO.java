package Matdol.SmartGazalBee.TBoard.Controller;

import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TBoardDTO {
    private Long id;
    private Long memberId;
    private String postTitle;
    private String postDevice;
    private int postPrice;
    private String postDescription;

    public TBoardDTO(Long id, String postTitle, String postDevice, int postPrice, String postDescription) {
        this.id = id;
        this.postTitle = postTitle;
        this.postDevice = postDevice;
        this.postPrice = postPrice;
        this.postDescription = postDescription;
    }

    public static TBoardDTO fromEntity(TBoard tBoard) {
        return new TBoardDTO(
                tBoard.getId(),
                //tBoard.getMember().getId(),
                tBoard.getPostTitle(),
                tBoard.getPostDevice(),
                tBoard.getPostPrice(),
                tBoard.getPostDescription()
        );
    }

    public TBoard toEntity() {
        return new TBoard(this.getMemberId(),
                this.getPostTitle(),
                this.getPostDevice(),
                this.getPostPrice(),
                this.getPostDescription());
    };
}