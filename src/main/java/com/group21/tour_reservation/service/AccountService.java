package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.repository.AccountRepository;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccount() {
        return accountRepository.findAllByStatus(1);
    }

    public Account getAccount(String slug) {

        return accountRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
    }


    public Account addAccount(Account Account) {
        Account.setStatus(1);
        return accountRepository.save(Account);
    }

    public Account editAccount(Account Account) {
        Account.setStatus(1);
        return accountRepository.save(Account);
    }

    public Account deleteAccount(String AccountId) {
        Account Account = accountRepository.findById( Integer.parseInt(AccountId)).orElseThrow(null);

        System.out.println(AccountId);
        if (Account == null) {
            return null;
        }
        Account.setStatus(0);

        return accountRepository.save(Account);
    }

    public Account getAccountByUserName(String username) {
        return accountRepository.findByUserName(username);
    }

}
