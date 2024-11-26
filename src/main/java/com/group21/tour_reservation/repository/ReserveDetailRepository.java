package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.Image;
import com.group21.tour_reservation.entity.ReserveDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveDetailRepository extends JpaRepository<ReserveDetail, Integer> {
}
