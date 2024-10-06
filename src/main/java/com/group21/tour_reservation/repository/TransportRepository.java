package com.group21.tour_reservation.repository;


import com.group21.tour_reservation.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport,Integer> {
    List<Transport> findAllByStatus(int status);
}
