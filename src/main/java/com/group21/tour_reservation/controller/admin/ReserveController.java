package com.group21.tour_reservation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group21.tour_reservation.service.ReserveService;

@Controller
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @GetMapping("/admin/reserve")
    public String transport(Model model) {
        model.addAttribute("reserves", reserveService.getAllReserve());
        return "admin/reserve/reserve.html";
    }
}
