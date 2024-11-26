package com.group21.tour_reservation.mapper;

import com.group21.tour_reservation.dto.response.TourScheduleTableResponse;
import com.group21.tour_reservation.entity.TourSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourScheduleMapper {

     TourScheduleTableResponse toTourScheduleAdResponse(TourSchedule tourSchedule);
}
