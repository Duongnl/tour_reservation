package com.group21.tour_reservation.service;

import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.entity.TransportDetail;
import com.group21.tour_reservation.repository.TourScheduleRepository;
import com.group21.tour_reservation.repository.TransportDetailRepository;
import com.group21.tour_reservation.repository.TransportRepository;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportDetailService {
    @Autowired
    private TransportDetailRepository transportDetailRepository;

    @Autowired
    private TourScheduleRepository tourScheduleRepository;
    @Autowired
    private TransportRepository transportRepository;

    public TransportDetail addTransportDetail(String scheduleSlug, String transportSlug, TransportDetail transportDetail) {
        TourSchedule schedule = tourScheduleRepository.findById(StringUtils.getIdFromSlug(scheduleSlug)).orElse(null);
        Transport transport = transportRepository.findById(StringUtils.getIdFirstFromSlug(transportSlug)).orElse(null);
        transportDetail.setTransport(transport);
        transportDetail.setTourSchedule(schedule);

        return transportDetailRepository.save(transportDetail);
    }

    public TransportDetail editTransportDetail(String scheduleSlug, String transportSlug, TransportDetail transportDetail) {
        TourSchedule schedule = tourScheduleRepository.findById(StringUtils.getIdFromSlug(scheduleSlug)).orElse(null);
        Transport transport = transportRepository.findById(StringUtils.getIdFirstFromSlug(transportSlug)).orElse(null);
        transportDetail.setTransport(transport);
        transportDetail.setTourSchedule(schedule);

        return transportDetailRepository.save(transportDetail);
    }

    public String deleteTransportDetail(Integer transportDetailId) {
        TransportDetail transportDetail = transportDetailRepository.findById(transportDetailId).orElse(null);
        String scheduleName = "";
        Integer scheduleId = 0;

        if (transportDetail != null) {
            scheduleName  = transportDetail.getTourSchedule().getScheduleName();
            scheduleId = transportDetail.getTourSchedule().getScheduleId();
            transportDetailRepository.delete(transportDetail);
        }

        return scheduleName +" "+scheduleId;

    }


}
