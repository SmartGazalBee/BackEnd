package Matdol.SmartGazalBee.Fcm.service;

import Matdol.SmartGazalBee.Common.Event.BoardEvent;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface BoardFcmService {
    void sendBoardWriterPush(BoardEvent event) throws FirebaseMessagingException;
}
