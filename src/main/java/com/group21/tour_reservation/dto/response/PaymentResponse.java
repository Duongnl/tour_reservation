package com.group21.tour_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse implements Serializable {

    private String status;
    private String message;
    private String URL;
    private int reserveId;


}
