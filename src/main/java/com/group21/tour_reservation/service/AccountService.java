package com.group21.tour_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository userRepository;

    public Account getAccountByUserName(String username) {
        return userRepository.findByUserName(username);
    }

}
