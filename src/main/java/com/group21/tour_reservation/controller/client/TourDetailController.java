package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.entity.Image;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.entity.TransportDetail;
import com.group21.tour_reservation.repository.ImgRepository;
import com.group21.tour_reservation.repository.TourScheduleRepository;
import com.group21.tour_reservation.repository.TransportRepository;
import com.group21.tour_reservation.service.TourScheduleService;
import com.group21.tour_reservation.service.TourService;
import com.group21.tour_reservation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TourDetailController {

    @Autowired
    TourScheduleService tourScheduleService;
    @Autowired
    private ImgRepository imgResponsitory;
    @Autowired
    private TourService tourService;

    @GetMapping("/tour/tour_detail/{slug}")
    public String tourDetail(Model model, @PathVariable("slug") String slug) {
        Tour tour = tourService.getTour(slug);
        if (tour == null) {
            return "admin/404.html";
        }
        System.out.println(tour.getTourId());
        model.addAttribute("tourDetail", tour);
        return "client/tour-detail-page.html";
    }

}