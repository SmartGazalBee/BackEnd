package Matdol.SmartGazalBee.Common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BeeResponse {
    //객체를 주지 않을 때
    private static ResponseBody toBody(MessageFormat messageFormat) {
        return ResponseBody.builder()
                .statusCode(messageFormat.getStatusCode())
                .description(messageFormat.getMessage())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")))
                .object(null)
                .build();
    }
    //객체를 줄때
    private static <T> ResponseBody toBody(MessageFormat messageFormat,T data) {
        return ResponseBody.<T>builder()
                .statusCode(messageFormat.getStatusCode())
                .description(messageFormat.getMessage())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")))
                .object(data)
                .build();
    }

    public static ResponseEntity<ResponseBody> toResponse(MessageFormat messageFormat) {
        return ResponseEntity.ok().body(toBody(messageFormat));
    }

    public static <T> ResponseEntity<ResponseBody<T>> toResponse(MessageFormat messageFormat, int status) {
        return ResponseEntity.status(status).body(toBody(messageFormat));
    }

    public static <T> ResponseEntity<ResponseBody<T>> toResponse(MessageFormat messageFormat, T data) {
        return ResponseEntity.ok().body(toBody(messageFormat, data));
    }
    public static <T> ResponseEntity<ResponseBody<T>> toResponse(MessageFormat messageFormat, T data, int status) {
        return ResponseEntity.status(status).body(toBody(messageFormat, data));
    }

    public static <T> ResponseEntity<ResponseBody<T>> toResponse(MessageFormat messageFormat, T data, int status, HttpHeaders headers) {
        return ResponseEntity.status(status).headers(headers).body(toBody(messageFormat,data));
    }
}
