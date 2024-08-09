package Matdol.SmartGazalBee.TBoard.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class TBoardDTO {
    private Long id;
    private Long purchaserId;
    private String postTitle;
    private String postDevice;
    private int postPrice;
    private String postDescription;
    private Long postHits = 0L;

}