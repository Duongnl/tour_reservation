package com.group21.tour_reservation.controller.admin;

import org.springframework.web.bind.annotation.RestController;
import com.group21.tour_reservation.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class EmployeeRestController {

    @Autowired
    private AccountRepository accountRepository;
    
    @PostMapping("/api/check-username")
    public Boolean CheckAccountExist(@RequestBody String username) {
        
        if(accountRepository.findAllByUserName(username).isEmpty()){
            return true;
        }
        return false;
    }
    
}
