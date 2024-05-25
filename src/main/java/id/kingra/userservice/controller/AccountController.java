package id.kingra.userservice.controller;

import id.kingra.userservice.dto.Request.CheckRegisterReqDto;
import id.kingra.userservice.dto.Request.VerificationReqDto;
import id.kingra.userservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/check-user-register")
    public ResponseEntity<?> checkUserRegister(@RequestBody CheckRegisterReqDto request) {
        log.debug("Request check user register : {}", request);
        return accountService.checkUserRegister(request);
    }

    @GetMapping("/load")
    public String loadBalancerTest() {
        return accountService.loadBalancerTest();
    }

    @PostMapping("/verification-otp")
    public ResponseEntity<?> verificationOtp(@RequestBody VerificationReqDto request) {
        return accountService.verificationOtp(request);
    }
}
