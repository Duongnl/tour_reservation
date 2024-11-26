package com.group21.tour_reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
// tao builder de tao mot doi tuong nhanh
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CusInfRequest {

    private LocalDate birthday;
    private String customerType;
    private String customerName;
    private String sex;

}
