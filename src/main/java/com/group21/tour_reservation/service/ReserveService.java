package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.Tour;
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

    public List<Reserve> getAllReserveByYear(int year) {
        return reserveRepository.findAllReserveByYear(year);
    }

    public List<Reserve> getAllReserveByYearAndMonth(int year, int month) {return reserveRepository.findAllReserveByYearAndMonth(year, month);}

    public Reserve getTour(String slug) {
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

    public Reserve addReserve(Reserve reserve) {
        reserve.setStatus(1);
        return reserveRepository.save(reserve);
    }
}
