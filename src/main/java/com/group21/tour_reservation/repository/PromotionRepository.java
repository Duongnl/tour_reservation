package com.group21.tour_reservation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group21.tour_reservation.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    List<Promotion> findAllByStatus(int status);
    // Truy vấn khách hàng có relationship_id là null và status bằng 0
    // List<Promotion> findByPromotionIsNullAndStatus(int status);
}
