package com.example.demo.service;

import com.example.demo.model.account.AccountDTO;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

  AccountDTO createAccount(AccountDTO accountDTO);

  AccountDTO viewDetail(Long id);

}
