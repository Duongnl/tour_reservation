package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.repository.ReserveRepository;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    public List<Reserve> getAllReserve() {
        return reserveRepository.findAllByStatus(1);
    }

}
