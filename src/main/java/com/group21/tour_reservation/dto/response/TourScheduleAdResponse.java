package com.group21.tour_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourScheduleAdResponse  {

    private Integer scheduleId;

    private String scheduleName;

    private LocalDate departureDate;

    private LocalDate returnDate;

    private int quantity;

    private Integer priceAdult;

    private Integer priceChild;

    private LocalDate visaExpire;

    private int status;

    private Integer quantityLeft;
    private Integer quantityReserved;
    private Integer priceAdultSale;
    private Integer priceChildSale;


}
