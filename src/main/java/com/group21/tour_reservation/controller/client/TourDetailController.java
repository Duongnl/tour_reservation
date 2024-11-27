package com.group21.tour_reservation.controller.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.service.TourScheduleService;
import com.group21.tour_reservation.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TourDetailController {

    @Autowired
    TourScheduleService tourScheduleService;
    @Autowired
    private TourService tourService;

    @GetMapping("/tour/tour_detail/{slug}")
    public String tourDetail(Model model, @PathVariable("slug") String slug, @AuthenticationPrincipal OAuth2User user) {

        if ( user != null) {
            model.addAttribute("name", user.getAttribute("name"));
        }

        Tour tour = tourService.getTour(slug);
        if (tour == null) {
            return "admin/404.html";
        }
        int minPrice = -1;
        List<Map<String, Object>> dataTourSchedules = new ArrayList<>();
        for (TourSchedule tourSchedule : tour.getTourSchedules()) {
            minPrice = tourService.minPriceSale(tourSchedule);
        }
        for (TourSchedule tourSchedule : tour.getTourSchedules()) {
            Map<String, Object> scheduleData = new HashMap<>();
            scheduleData.put("scheduleId", tourSchedule.getScheduleId());
            scheduleData.put("date", tourSchedule.getDepartureDate());
            scheduleData.put("price", tourService.minPriceSale(tourSchedule));
            dataTourSchedules.add(scheduleData);
            if (minPrice > tourService.minPriceSale(tourSchedule)) {
                minPrice = tourService.minPriceSale(tourSchedule);
            }
        }
        String minPriceString = minPrice+" đ / Khách";
        System.out.println("DataTourSchedules: aaaaaaaaaaaaaaaaaaaaaaaaaaaaa bbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        for (Map<String, Object> schedule : dataTourSchedules) {
            System.out.println(schedule);
        }
        model.addAttribute("scheduleData", dataTourSchedules);
        model.addAttribute("tourDetail", tour);
        model.addAttribute("minPrice", minPriceString);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String schedulesJson = objectMapper.writeValueAsString(dataTourSchedules);
            model.addAttribute("schedulesJson", schedulesJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "client/tour-detail-page.html";
    }

}