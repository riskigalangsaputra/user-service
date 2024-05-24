package id.kingra.userservice.service;

import id.kingra.userservice.dto.Request.CheckRegisterReqDto;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<?> checkUserRegister(CheckRegisterReqDto request);

    String loadBalancerTest();
}
