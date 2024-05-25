package id.kingra.userservice.dto.Request;

import lombok.Data;

@Data
public class VerificationReqDto {

    private String email;
    private String otp;
}
