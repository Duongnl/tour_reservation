package com.group21.tour_reservation.controller.admin;

import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.entity.TransportDetail;
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
public class TourScheduleController {

    @Autowired
    TourScheduleService tourScheduleService;
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private TourService tourService;

    @GetMapping("/admin/tour/schedule/add/{slug}")
    public String tourScheduleAddView(Model model, @PathVariable("slug") String slug) {
        Tour tour = tourService.getTour(slug);
        if (tour == null) {
            return "admin/404.html";
        }
        TourSchedule schedule = new TourSchedule();
        schedule.setPriceAdult(0);
        schedule.setPriceChild(0);
        model.addAttribute("schedule", schedule);
        model.addAttribute("tourId", tour.getTourId());
        return "admin/schedule/schedule-add.html";
    }

    @GetMapping("/admin/tour/schedule/{slug}")
    public String tourScheduleEditView(Model model, @PathVariable("slug") String slug) {
        TourSchedule schedule = tourScheduleService.getSchedule(slug);
        if (schedule == null) {
            return "admin/404.html";
        }
        model.addAttribute("schedule", schedule);
        return "admin/schedule/schedule-edit.html";
    }


    @PostMapping("/admin/tour/schedule/add-schedule")
    public String addSchedule(Model model,
                              @RequestParam("tour-id") Integer tourId,
                              @ModelAttribute("schedule") TourSchedule schedule,
                              RedirectAttributes redirectAttributes
    ) {
        TourSchedule scheduleRes = tourScheduleService.addSchedule(schedule, tourId);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới lịch trình thành công");
        return "redirect:/admin/tour/schedule/schedule-overview/" + StringUtils.createSlug(scheduleRes.getScheduleName()) + "-" + scheduleRes.getScheduleId();
    }

    @GetMapping("/admin/tour/schedule/schedule-overview/{slug}")
    public String scheduleOverView(Model model, @PathVariable("slug") String slug) {
        TransportDetail transportDetailNew = new TransportDetail();
        TourSchedule schedule = tourScheduleService.getSchedule(slug);
        if (schedule == null) {
            return "admin/404.html";
        }
        List<TransportDetail> transportDepartures = new ArrayList<>();
        List<TransportDetail> transportReturns = new ArrayList<>();

        schedule.getTransportDetails().forEach(transportDetail -> {
            if (transportDetail.getStatus() == 1) {
                transportDepartures.add(transportDetail);
            } else if (transportDetail.getStatus() == 2) {
                transportReturns.add(transportDetail);
            }
        });

        List<Transport> transports = transportRepository.findAllByStatus(1);
        List<String> states = new ArrayList<>();
        for (Transport transport : transports) {
            states.add(transport.getTransportId()+"-"+transport.getTransportName()
                    +"-"+transport.getDepartureLocation()
                    +" -> "+transport.getDestinationLocation());
        }
        model.addAttribute("states", states);


        model.addAttribute("schedule", schedule);
        model.addAttribute("transportDepartures", transportDepartures);
        model.addAttribute("transportReturns", transportReturns);
        model.addAttribute("transportDetail", transportDetailNew);

        return "admin/schedule/schedule-overview.html";
    }

    @PostMapping("/admin/tour/schedule/edit-schedule")
    public String editSchedule(@ModelAttribute("schedule") TourSchedule schedule,
                               RedirectAttributes redirectAttributes,
                               Model model,
                               @RequestParam("tour-id") Integer tourId,
                               @RequestParam("departure-date") LocalDate departureDate,
                               @RequestParam("return-date") LocalDate returnDate,
                               @RequestParam(name = "visa-expire", required = false) LocalDate visaExpire
    ) {
        System.out.println("controoler >>> "+ tourId);
        TourSchedule scheduleRes = tourScheduleService.editSchedule(schedule, departureDate, returnDate, visaExpire, tourId);
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa lịch trình thành công");
        return "redirect:/admin/tour/schedule/schedule-overview/" + StringUtils.createSlug(scheduleRes.getScheduleName()) + "-" + scheduleRes.getScheduleId();
    }

    @GetMapping("/admin/tour/schedule/delete-schedule/{id}")
    public String deleteSchedule(@PathVariable("id") Integer id, Model model
            ,   RedirectAttributes redirectAttributes) {
        TourSchedule schedule = tourScheduleService.deleteSchedule(id);
        if (schedule != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/tour/tour-overview/" + StringUtils.createSlug(schedule.getTour().getTourName()) + "-" + schedule.getTour().getTourId();
    }

}