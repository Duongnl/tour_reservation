package com.group21.tour_reservation.mapper;

import com.group21.tour_reservation.dto.response.TourCardResponse;
import com.group21.tour_reservation.dto.response.TourReserveResponse;
import com.group21.tour_reservation.entity.Tour;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourMapper {
    
    TourCardResponse toTourCardResponse(Tour tour);

    TourReserveResponse toTourReserveResponse(Tour tour);
}
