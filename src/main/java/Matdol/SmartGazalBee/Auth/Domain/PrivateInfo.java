package Matdol.SmartGazalBee.Auth.Domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PrivateInfo {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String memberName;

    private String email;

    private String RRN; //Resident Registration Number

    private String EID; //Employer ID number

    private String loginId;

    private String loginPw;

    protected PrivateInfo() {}
}
