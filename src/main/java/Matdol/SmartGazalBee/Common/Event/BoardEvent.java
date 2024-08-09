package Matdol.SmartGazalBee.Common.Event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardEvent {
    private final Long postId;
    private final Long parentCommentId;
    private final String content;
    private final Long purchaserId;


}
