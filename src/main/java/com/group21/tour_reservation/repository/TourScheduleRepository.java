package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourScheduleRepository extends JpaRepository<TourSchedule, Integer> {
}
