package id.kingra.userservice.service.impl;

import id.kingra.userservice.dto.Request.CheckRegisterReqDto;
import id.kingra.userservice.entity.Account;
import id.kingra.userservice.entity.redis.TempAccount;
import id.kingra.userservice.repository.AccountRepository;
import id.kingra.userservice.repository.redis.TempAccountRepository;
import id.kingra.userservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TempAccountRepository tempAccountRepository;

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
        return ResponseEntity.ok().build();
    }
}