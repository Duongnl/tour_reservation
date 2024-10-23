package com.group21.tour_reservation.mapper;


import com.group21.tour_reservation.dto.response.TourScheduleAdResponse;
import com.group21.tour_reservation.entity.TourSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourScheduleMapper {

     TourScheduleAdResponse tourScheduleAdResponse (TourSchedule tourSchedule);
}
