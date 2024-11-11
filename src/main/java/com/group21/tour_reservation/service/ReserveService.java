package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.repository.ReserveRepository;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    public List<Reserve> getAllReserve() {
        return reserveRepository.findAll();
    }

    public Reserve getReserve(String slug) {
        Reserve reserve = reserveRepository.findById(
                com.group21.tour_reservation.utils.StringUtils.getIdFromSlug(slug)).orElse(null);

        return reserve;
    }

    public Reserve deleteReserve(Integer reserveID) {
        Reserve reserve = reserveRepository.findById(reserveID).orElseThrow(null);
        if (reserve != null) {
            reserve.setStatus(0);
        } else {
            return null;
        }
        return reserveRepository.save(reserve);
    }

    public Reserve addReserve(Integer reserveID) {
        Reserve reserve = reserveRepository.findById(reserveID).orElseThrow(null);
        if (reserve != null) {
            reserve.setStatus(2);
        } else {
            return null;
        }
        return reserveRepository.save(reserve);
    }

}
