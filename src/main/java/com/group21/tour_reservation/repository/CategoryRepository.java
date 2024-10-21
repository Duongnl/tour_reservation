package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByStatus(int status);
}
