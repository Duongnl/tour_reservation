package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findAllByStatus(int status);
}
