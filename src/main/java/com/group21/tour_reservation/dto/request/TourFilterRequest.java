package com.group21.tour_reservation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// tao get set hashcode euqual,...
@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
// auto them private vao cac bien kh khai bao
public class TourFilterRequest {

    private Integer category;
    private String departureLocation;
    private String destinationLocation;
    private String departureDate;
    private String price;
}
