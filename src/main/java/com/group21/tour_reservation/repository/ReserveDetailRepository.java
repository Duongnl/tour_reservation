package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.ReserveDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveDetailRepository extends JpaRepository<Reserve, Integer> {
    List<ReserveDetail> findAllByStatus(int status);
}
