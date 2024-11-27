package com.group21.tour_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveResponse {
    private Integer code;
    private String message;
    private int reserveId;
}
