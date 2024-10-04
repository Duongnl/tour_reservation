package com.group21.tour_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TourController {

    @GetMapping("/admin/tour")
    public String getTours (Model model) {
        return "/admin/tour/tour.html";
    }

}
