package Matdol.SmartGazalBee.Fcm.service;

import Matdol.SmartGazalBee.Common.Event.BoardEvent;
import Matdol.SmartGazalBee.Fcm.Builder.MessageBuilder;
import Matdol.SmartGazalBee.TBoard.Domain.TBoard;
import Matdol.SmartGazalBee.TBoard.Repository.TBoardRepository;
import Matdol.SmartGazalBee.User.Domain.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BoardFcmServiceImpl implements BoardFcmService{
    private final TBoardRepository tBoardRepository;
    private final MessageBuilder mb;
    @Override
    @Async
    @TransactionalEventListener
    public void sendBoardWriterPush(BoardEvent event) throws FirebaseMessagingException {
        Optional<TBoard> boardEntity = tBoardRepository.findById(event.getPostId());

        // 게시글이 없거나 댓글 이벤트가 아니면 메서드 종료
        if (boardEntity.isEmpty()) {
            return;
        }

        TBoard board = boardEntity.get();
        User purchaser = board.getPurchaser();

        // 메시지 생성
        String templateKey = "post.writer.comment.push";
        Object[] args = {purchaser.getNickname(), board.getPostTitle(), event.getContent()};
        /*사용자 fcm token 가져오기*/
        String fcmToken = purchaser.getFcmToken(); // 임의로 작성

        Message commentMessage = mb.buildMessage(templateKey, args, fcmToken);


        // FCM을 통해 메시지 전송
        String response = FirebaseMessaging.getInstance().send(commentMessage);
    }
}

