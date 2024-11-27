package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.dto.request.ReserveRequest;
import com.group21.tour_reservation.dto.request.TourFilterRequest;
import com.group21.tour_reservation.dto.response.ReserveResponse;
import com.group21.tour_reservation.dto.response.TourCardResponse;
import com.group21.tour_reservation.service.ReserveService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservePageRestController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping("/api/client/reserve")
    public ReserveResponse FilterTours (@RequestBody ReserveRequest reserveRequest, HttpServletRequest request) {

        return reserveService.reserve(reserveRequest, request);
    }
}
