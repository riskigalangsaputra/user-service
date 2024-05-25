package id.kingra.userservice.service.impl;

import feign.FeignException;
import id.kingra.userservice.dto.Request.CheckRegisterReqDto;
import id.kingra.userservice.dto.Request.OtpReqDto;
import id.kingra.userservice.dto.Request.VerificationReqDto;
import id.kingra.userservice.entity.Account;
import id.kingra.userservice.entity.redis.TempAccount;
import id.kingra.userservice.feignclient.OTPClient;
import id.kingra.userservice.repository.AccountRepository;
import id.kingra.userservice.repository.redis.TempAccountRepository;
import id.kingra.userservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TempAccountRepository tempAccountRepository;
    private final OTPClient otpClient;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TempAccountRepository tempAccountRepository, OTPClient otpClient) {
        this.accountRepository = accountRepository;
        this.tempAccountRepository = tempAccountRepository;
        this.otpClient = otpClient;
    }

    @Override
    public ResponseEntity<?> checkUserRegister(CheckRegisterReqDto request) {
        final String email = request.getEmail();
        Optional<Account> opAccount = accountRepository.getFirstByEmail(email);
        if (opAccount.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Optional<TempAccount> opTempAccount = tempAccountRepository.getFirstByEmail(email);
        if (opTempAccount.isPresent()) {
            return ResponseEntity.ok().build();
        }

        TempAccount tempAccount = new TempAccount();
        tempAccount.setEmail(email);
        tempAccount.setValid(false);
        tempAccountRepository.save(tempAccount);

        try {
            OtpReqDto otpReqDto = new OtpReqDto();
            otpReqDto.setEmail(email);
            otpClient.requestOTP(otpReqDto);
        } catch (FeignException.FeignClientException e) {
            return ResponseEntity.status(e.status()).body(e.contentUTF8());
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public String loadBalancerTest() {
        return otpClient.testLoadBalancer();
    }

    @Override
    public ResponseEntity<?> verificationOtp(VerificationReqDto request) {
        Optional<TempAccount> opTemptAccount = tempAccountRepository.getFirstByEmail(request.getEmail());
        if (opTemptAccount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            otpClient.verificationOtp(request);
        } catch (FeignException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(ex.status()).body(ex.contentUTF8());
        }

        TempAccount tempAccount = opTemptAccount.get();
        tempAccount.setValid(true);
        tempAccountRepository.save(tempAccount);
        return ResponseEntity.ok().build();
    }
}
