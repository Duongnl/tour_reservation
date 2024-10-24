package com.group21.tour_reservation.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.entity.Tour;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.service.ReserveService;

@Controller
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @GetMapping("/admin/reserve")
    public String transport(Model model) {
        model.addAttribute("reserve", reserveService.getAllReserve());
        return "admin/reserve/reserve.html";
    }

    @GetMapping("/admin/reserve/delete-reserve/{id}")
    public String deleteReserve(Model model, @PathVariable("id") Integer reserveID,
            RedirectAttributes redirectAttributes) {
        if (reserveService.deleteReserve(reserveID) != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/tour";
    }

    @GetMapping("/admin/reserve/add-reserve/{id}")
    public String addReserve(Model model, @PathVariable("id") Integer reserveID,
            RedirectAttributes redirectAttributes) {
        if (reserveService.addReserve(reserveID) != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Duyệt thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/tour";
    }
}
