package Matdol.SmartGazalBee.Fcm.Builder;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageBuilder {

    private final MessageSource messageSource;

    /**
     * FCM 메시지를 생성합니다.
     *
     * @param templateKey 메시지 템플릿 키
     * @param args        메시지 템플릿에 삽입할 인자들
     * @param fcmToken    수신자의 FCM 토큰
     * @return            구성된 FCM 메시지
     */
    public Message buildMessage(String templateKey, Object[] args, String fcmToken) {
        // 메시지 콘텐츠를 템플릿 키와 인자들로 생성
        String messageContent = messageSource.getMessage(templateKey, args, Locale.getDefault());

        // 메시지 제목과 본문 설정
        Notification notification = Notification.builder()
                .setTitle("Notification Title") // 제목을 고정값으로 설정하거나 동적으로 설정할 수 있음
                .setBody(messageContent)
                .build();

        return Message.builder()
                .setNotification(notification)
                .setToken(fcmToken)
                .build();
    }
}
