package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.dto.response.TourReserveResponse;
import com.group21.tour_reservation.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    @Autowired
    private TourService tourService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "client/home.html";
    }

    @GetMapping("/tour")
    public String tourView() {

        return "client/tour-page.html";
    }

    @GetMapping("/reserve/{slug}")
    public String reserveView(Model model, @PathVariable("slug") String slug) {
        TourReserveResponse tourReserveResponse = tourService.getTourReserveClient(slug);
        if (tourReserveResponse == null) {
            return "admin/404.html";
        }
        model.addAttribute("tourReserveResponse", tourReserveResponse);
        return "client/reserve-page.html";
    }



}
