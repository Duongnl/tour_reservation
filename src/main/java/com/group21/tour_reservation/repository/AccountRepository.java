package com.group21.tour_reservation.repository;


import com.group21.tour_reservation.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group21.tour_reservation.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
List<Account> findAllByStatus(int status);
    Account findByUserName(String userName);
    
    List<Account> findAllByUserName(String userName);
}
