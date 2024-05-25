package id.kingra.userservice.feignclient;

import id.kingra.userservice.dto.Request.OtpReqDto;
import id.kingra.userservice.dto.Request.VerificationReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * This class is list for api OTP service
 **/

//@FeignClient(value = "otp", url = "http://localhost:8016") -> specific service
@FeignClient(value = "otp-service") /* without url, because routing handling using load balancer */
public interface OTPClient {
    @PostMapping("/otp/request")
    ResponseEntity<?> requestOTP(@RequestBody OtpReqDto request);

    @GetMapping("/test-load-balancer")
    String testLoadBalancer();

    @PostMapping("/verification-otp")
    ResponseEntity<?> verificationOtp(@RequestBody VerificationReqDto request);
}
