package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.ReserveDetail;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.repository.ReserveDetailRepository;
import com.group21.tour_reservation.repository.ReserveRepository;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReserveDetailService {
    @Autowired
    private ReserveDetailRepository reserveDetailRepository;
    @Autowired
    private ReserveRepository reserveRepository;

    public List<Reserve> getAllReserveDetail() {
        return reserveDetailRepository.findAll();
    }

    public Reserve getReserve(String slug) {
        Reserve reserve = reserveRepository.findById(
                com.group21.tour_reservation.utils.StringUtils.getIdFromSlug(slug)).orElse(null);

        return reserve;
    }
   
}
