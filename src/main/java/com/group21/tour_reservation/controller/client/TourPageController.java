package com.group21.tour_reservation.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TourPageController {

    @GetMapping("/tour")
    public String tourView() {

        return "client/tour-page.html";
    }

}
