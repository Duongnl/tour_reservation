package com.group21.tour_reservation.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.ReserveDetail;
import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.entity.TransportDetail;
import com.group21.tour_reservation.service.ReserveDetailService;
import com.group21.tour_reservation.service.ReserveService;

@Controller
public class ReserveDetailController {
    @Autowired
    private ReserveDetailService reserveDetailService;
    @Autowired
    private ReserveService reserveService;

    @GetMapping("/admin/reserve/reserve-detail/{slug}")
    public String reserveDetail(Model model, @PathVariable("slug") String slug) {
        Reserve rs = reserveService.getReserve(slug);

        model.addAttribute("reserveDetail", rs.getReserveDetails());
        return "admin/reserve/reserve-detail.html";
    }
}
