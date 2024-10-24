package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends JpaRepository<Image, Integer> {
}
