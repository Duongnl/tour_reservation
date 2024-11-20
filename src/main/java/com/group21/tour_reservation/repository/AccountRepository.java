package com.group21.tour_reservation.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group21.tour_reservation.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findByUserName(String userName);
    
    List<Account> findAllByUserName(String userName);
}
