package com.group21.tour_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourPageResponse {
    private Integer page;
    private Integer pageCurrent;
    private Integer cardTotal;
    private List<TourCardResponse> tourCardResponses;
}
