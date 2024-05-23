package id.kingra.userservice.controller;

import id.kingra.userservice.dto.Request.CheckRegisterReqDto;
import id.kingra.userservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/check-user-register")
    public ResponseEntity<?> checkUserRegister(@RequestBody CheckRegisterReqDto request) {
        log.debug("Request check user register : {}", request);
        return accountService.checkUserRegister(request);
    }
}
