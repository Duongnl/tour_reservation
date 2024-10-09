package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.Reserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
    List<Reserve> findAllByStatus(int status);
}
