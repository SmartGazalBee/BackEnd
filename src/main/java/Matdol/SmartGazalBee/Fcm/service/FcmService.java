package Matdol.SmartGazalBee.Fcm.service;

import Matdol.SmartGazalBee.Fcm.Dto.FcmRequestDto;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface FcmService {
    void sendMessage(FcmRequestDto fcmRequestDto) throws FirebaseMessagingException;
}
