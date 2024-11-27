package com.group21.tour_reservation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.group21.tour_reservation.entity.Promotion;
import com.group21.tour_reservation.entity.TourSchedule;

import jakarta.transaction.Transactional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    List<Promotion> findAllByStatus(int status);

    // List<Promotion> findByPromotionIsNullAndStatus(int status);
    List<TourSchedule> findAllByStatus(Integer status);

    @Query("SELECT ts FROM TourSchedule ts JOIN ts.promotions p WHERE p.id = :promotionId")
    List<TourSchedule> findSchedulesByPromotionId(@Param("promotionId") String promotionId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM promotion_detail WHERE promotion_id = :promotionId AND schedule_id = :scheduleId", nativeQuery = true)
    void removeTourScheduleFromPromotion(@Param("promotionId") Integer promotionId,
            @Param("scheduleId") Integer scheduleId);

}
