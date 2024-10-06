package com.group21.tour_reservation.controller;

import com.group21.tour_reservation.entity.Transport;
import com.group21.tour_reservation.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TransportController {

    @Autowired
    private TransportService transportService;

    @GetMapping("/admin/transport")
    public String transport(Model model) {
        model.addAttribute("transports", transportService.getAllTransports());
        return "admin/transport/transport.html";
    }

    @GetMapping("/admin/transport/add")
    public String transportAddView(Model model) {
        Transport transport = new Transport();
        model.addAttribute("transport", transport); // Thêm đối tượng vào mô hình
        return "admin/transport/transport-add.html";
    }

    @GetMapping("/admin/transport/{slug}")
    public String transportEditView(Model model,@PathVariable("slug") String slug) {
        Transport transport = transportService.getTransport(slug);
        if (transport == null) {
            return "admin/404.html";
        }

        model.addAttribute("transport", transport); // Thêm đối tượng vào mô hình
        return "admin/transport/transport-edit.html";
    }

    @PostMapping("/admin/transport/add-transport")
    public String addTransport(Model model, @ModelAttribute("transport") Transport transport, RedirectAttributes redirectAttributes) {
        transportService.addTransport(transport);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới phương tiện thành công!");
        return "redirect:/admin/transport";
    }

    @PostMapping("/admin/transport/edit-transport")
    public String editTransport(Model model, @ModelAttribute("transport") Transport transport, RedirectAttributes redirectAttributes) {
        transportService.editTransport(transport);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa phương tiện thành công!");
        return "redirect:/admin/transport";
    }

    @GetMapping("/admin/transport/delete-transport/{id}")
    public String deleteTransport(Model model,@PathVariable("id") String transportId, RedirectAttributes redirectAttributes) {
        if (transportService.deleteTransport(transportId) != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/transport";
    }






}
