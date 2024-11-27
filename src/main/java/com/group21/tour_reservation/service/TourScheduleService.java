package com.group21.tour_reservation.service;

import com.group21.tour_reservation.dto.response.TourReserveResponse;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.mapper.TourMapper;
import com.group21.tour_reservation.repository.TourRepository;
import com.group21.tour_reservation.repository.TourScheduleRepository;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TourScheduleService {
    @Autowired
    private TourScheduleRepository tourScheduleRepository;

    @Autowired
    private TourRepository tourRepository;

    public TourSchedule addSchedule(TourSchedule schedule, Integer tourId) {
        Tour tour = tourRepository.findById(tourId).orElse(null);
        schedule.setTour(tour);
        schedule.setStatus(1);
        return tourScheduleRepository.save(schedule);
    }

    public TourSchedule getSchedule(String slug) {
        TourSchedule schedule = tourScheduleRepository.findById(StringUtils.getIdFromSlug(slug)).orElse(null);
        if (schedule == null) {
            return null;
        }

        return schedule;
    }

    public TourSchedule editSchedule(TourSchedule schedule, LocalDate departureDate,
                                    LocalDate returnDate, LocalDate visaExpire,
                                    Integer tourId) {

        TourSchedule touScheduleOld = tourScheduleRepository.findById(schedule.getScheduleId()).orElse(null);
        System.out.println(">>>> id"+ tourId);
        Tour tour = tourRepository.findById(tourId).orElse(null);
        System.out.println("Schedule Id : " + schedule.getScheduleId());
        schedule.setTour(tour);
        schedule.setDepartureDate(departureDate);
        schedule.setReturnDate(returnDate);
        schedule.setPromotions(touScheduleOld.getPromotions());
        if (visaExpire != null) {
        schedule.setVisaExpire(visaExpire);
        }
        schedule.setStatus(1);
        return tourScheduleRepository.save(schedule);
    }

    public TourSchedule deleteSchedule(Integer id) {
        TourSchedule schedule = tourScheduleRepository.findById(id).orElseThrow(null);
        if (schedule != null) {
            schedule.setStatus(0);
        } else {
            return null;
        }
        return tourScheduleRepository.save(schedule);
    }
}
