package Matdol.SmartGazalBee.Fcm.service;

import Matdol.SmartGazalBee.Fcm.Dto.FcmRequestDto;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class FcmServiceImpl implements FcmService{
    private final MessageSource messageSource;
    @Override
//    @Async
//    @TransactionalEventListener
    public void sendMessage(FcmRequestDto fcmRequestDto) throws FirebaseMessagingException {

        Message message = getMessage(fcmRequestDto.getTitle(), fcmRequestDto.getBody(), fcmRequestDto.getToken());
        FirebaseMessaging.getInstance().send(message);
    }

    private Message getMessage(String title,String message, String fcmToken)
    {
        String basicTitle = messageSource.getMessage("search",null, Locale.getDefault());
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(message)
                .build();
        return Message.builder()
                .setNotification(notification)
                .setToken(fcmToken)
                .build();
    }
}
