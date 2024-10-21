package com.group21.tour_reservation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group21.tour_reservation.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    
}
