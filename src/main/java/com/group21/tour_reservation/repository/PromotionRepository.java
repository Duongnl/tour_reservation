package com.group21.tour_reservation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.group21.tour_reservation.entity.Promotion;
import com.group21.tour_reservation.entity.TourSchedule;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    List<Promotion> findAllByStatus(int status);
    // List<Promotion> findByPromotionIsNullAndStatus(int status);
    List <TourSchedule> findAllByStatus(Integer status); 

    @Query("DELETE FROM Promotion p JOIN p.tourSchedules ts WHERE p.id = :promotionId AND ts.id = :scheduleId")
    void removeTourScheduleFromPromotion(@Param("promotionId") Integer promotionId, @Param("scheduleId") Integer scheduleId);
}
