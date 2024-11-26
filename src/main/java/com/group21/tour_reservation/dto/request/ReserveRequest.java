package com.group21.tour_reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveRequest {
    private int tourScheduleId;
    private String address;
    private String email;
    private String customerName;
    private String phoneNumber;
    private String reserveDetail;
    List<CusInfRequest> customers;
}
