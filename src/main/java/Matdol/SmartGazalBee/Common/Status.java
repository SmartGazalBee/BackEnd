package Matdol.SmartGazalBee.Common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status implements MessageFormat{
    RUN("RU01", "execution clear"),
    FIND("FI01", "find clear")
    ;
    String statusCode;
    String message;
}
