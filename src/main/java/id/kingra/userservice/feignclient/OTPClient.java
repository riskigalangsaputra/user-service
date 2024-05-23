package id.kingra.userservice.feignclient;

import id.kingra.userservice.dto.Request.OtpReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "otp", url = "http://localhost:8016")
public interface OTPClient {
    @PostMapping("/otp/request")
    ResponseEntity<?> requestOTP(@RequestBody OtpReqDto request);
}
