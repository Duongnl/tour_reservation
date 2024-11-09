package com.group21.tour_reservation.repository;

import com.group21.tour_reservation.entity.Reserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
    List<Reserve> findAllByStatus(int status);
//    ;
//    @Query("SELECT * FROM reserve WHERE YEAR(time) = year")
//    List<Reserve> findAllReserveByYear(int year);

    @Query("SELECT r FROM Reserve r WHERE YEAR(r.time) = :year")
    List<Reserve> findAllReserveByYear(@Param("year") int year);

    @Query("SELECT r FROM Reserve r WHERE YEAR(r.time) = :year AND MONTH(r.time) = :month")
    List<Reserve> findAllReserveByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
