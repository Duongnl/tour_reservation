package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.TourSchedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourScheduleRepository extends JpaRepository<TourSchedule, Integer> {
     List <TourSchedule> findAllByStatus(int status); 

     @Query("SELECT ts FROM TourSchedule ts JOIN ts.promotions p WHERE p IS NOT NULL")
     List<TourSchedule> findSchedulesWithPromotions();
}
