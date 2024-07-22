package Matdol.SmartGazalBee.Fcm.Dto;

import lombok.*;


@Getter
@ToString
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class FcmRequestDto {
    private String token;

    private String title;

    private String body;

    @Builder(toBuilder = true)
    public FcmRequestDto(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }
}
