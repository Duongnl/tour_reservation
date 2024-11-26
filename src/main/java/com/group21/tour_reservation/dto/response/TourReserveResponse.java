package com.group21.tour_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourReserveResponse {
    private String tourName; // tên phương tour
    private String departureLocation; // tên địa điểm xuất phát của tour
    private String country; // tên địa điểm đến của tour

    private String name;
    private String email;
    private String phone;
    private String address;

    private LocalDate departureDate; // ngày khởi hành
    private Integer quantityLeft; // số lượng còn lại
    private Integer priceAdult;
    private Integer priceChild;
    private TransportReserveResponse transportDeparture;
    private TransportReserveResponse transportReturn;
}
