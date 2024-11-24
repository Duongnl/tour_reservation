package com.group21.tour_reservation.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group21.tour_reservation.entity.Promotion;
import com.group21.tour_reservation.entity.TourSchedule;
import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.service.PromotionService;

@Controller
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @GetMapping("/admin/promotion")
    public String Getpromotion(Model model) {
        model.addAttribute("promotions", promotionService.getAllPromotion());
        return "admin/promotion/promotion.html";
    }

    @GetMapping("/admin/promotion/add")
    public String promotionAddView(Model model) {
        Promotion promotion = new Promotion();
        model.addAttribute("promotion", promotion); // Thêm đối tượng vào mô hình
        return "admin/promotion/promotion-add.html";
    }

    @PostMapping("/admin/promotion/add-promotion")
    public String addPromotion(Model model, @ModelAttribute("promotion") Promotion promotion,
            RedirectAttributes redirectAttributes) {
        promotionService.createPromotion(promotion);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới khuyến mãi thành công!");
        return "redirect:/admin/promotion";
    }

    @GetMapping("/admin/promotion/{slug}")
    public String promotionEditView(Model model, @PathVariable("slug") String slug) {
        Promotion promotion = promotionService.getPromotion(slug);
        if (promotion == null) {
            return "admin/404.html";
        }

        model.addAttribute("promotion", promotion); // Thêm đối tượng vào mô hình
        return "admin/promotion/promotion-edit.html";
    }

    @PostMapping("/admin/promotion/edit-promotion")
    public String editPromotion(Model model, @ModelAttribute("promotion") Promotion promotion,
            RedirectAttributes redirectAttributes) {
        promotionService.editPromotion(promotion);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa khuyến mãi thành công!");
        return "redirect:/admin/promotion";
    }

    @GetMapping("/admin/promotion/delete-promotion/{id}")
    public String deletePromotion(Model model, @PathVariable("id") String promotionId,
            RedirectAttributes redirectAttributes) {
        if (promotionService.deletePromotion(promotionId) != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/promotion";
    }

    @GetMapping("/admin/promotion/add-promotion-to-tour/{id}")
    public String GetTourShedule(Model model, @PathVariable("id") String promotionId) {
        // System.out.println("123");
        List<TourSchedule> schedules = promotionService.getAllShedules();
        List<TourSchedule> promotionSchedule = promotionService.getAllSchedulesWithPromotions();
        model.addAttribute("schedules", schedules);
        model.addAttribute("promotionchedule", promotionSchedule);
        model.addAttribute("promotionId", promotionId); // Thêm promotionId vào model
        // System.out.println("456");
        return "admin/promotion/promotion-add-tourschedule.html";
    }

    @PostMapping("/admin/promotion/add-promotion-to-tour")
    public String addPromotionTour(Model model, @RequestParam Integer promotionId, @RequestParam Integer scheduleId,
            RedirectAttributes redirectAttributes) {
        try {
            promotionService.addPromotionToSchedule(promotionId, scheduleId);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm khuyến mãi thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm khuyến mãi!");
        }

        return "redirect:/admin/promotion";
    }

    @PostMapping("/admin/promotion/add-promotion-to-tour/delete")
    public String deletePromotionDetail(@RequestParam("promotionId") Integer promotionId,
            @RequestParam("scheduleId") Integer scheduleId) {
        promotionService.deletePromotionDetail(promotionId, scheduleId);
        return "redirect:/admin/promotion";
    }
}
