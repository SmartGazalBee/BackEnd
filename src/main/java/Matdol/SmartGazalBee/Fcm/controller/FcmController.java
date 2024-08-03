package Matdol.SmartGazalBee.Fcm.controller;

import Matdol.SmartGazalBee.Fcm.Dto.FcmRequestDto;
import Matdol.SmartGazalBee.Fcm.service.FcmService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fcm")
public class FcmController {

    private final FcmService fcmService;

    @PostMapping("/send")
    public ResponseEntity<String> sendFcmMessage(@RequestBody FcmRequestDto fcmRequestDto) {
        try {
            fcmService.sendMessage(fcmRequestDto);
            return ResponseEntity.ok("FCM message sent successfully");
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(500).body("Failed to send FCM message: " + e.getMessage());
        }
    }
}