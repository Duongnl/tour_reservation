package com.group21.tour_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourCardResponse {


    private Integer tourId;

    private String tourName;;

    private String tourDetail;

    private String departureLocation;

    private String city;

    private String country;

    private String imageMain;

    private Integer price;

    private Integer priceSale;

    private Set<String> departureDates;

    private int status;


}
