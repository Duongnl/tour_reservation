package com.group21.tour_reservation.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountService userService;

    public CustomUserDetailsService(AccountService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.group21.tour_reservation.entity.Account  user = this.userService.getAccountByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // TODO Auto-generated method stub
        return new User(
                user.getUserName(),
                user.getPassword(),
                //Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+user.getRole())));

    }
}
