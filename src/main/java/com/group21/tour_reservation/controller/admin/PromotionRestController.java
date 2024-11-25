package com.group21.tour_reservation.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.service.PromotionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PromotionRestController {
    @Autowired
    private PromotionService promotionService;

    @PostMapping("/api/add-promotion-to-tour-schedules")
    public List<TourSchedule> getTourSchedules() {
        return promotionService.getAllShedules();
    }
}
