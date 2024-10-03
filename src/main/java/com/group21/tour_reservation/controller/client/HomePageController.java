package com.group21.tour_reservation.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomePageController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "client/layout/home-page.html";
    }
    

}
