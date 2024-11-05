package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.entity.Category;
import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PersonalInfoController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/personal-info")
    public String index(Model model) {
        model.addAttribute("customer", customerService.getCustomer("1"));
        return "client/customer/index.html";
    }
    @GetMapping("/personal-info/edit/{slug}")
    public String EditView(Model model,@PathVariable("slug") String slug) {
        Customer customer = customerService.getCustomer(slug);
        if (customer == null) {
            return "admin/404.html";
        }

        model.addAttribute("customer", customer); // Thêm đối tượng vào mô hình
        return "client/customer/edit.html";
    }
    @PostMapping("/personal-info/edit")
    public String edit(Model model, @ModelAttribute("customer") Customer customer,
                       RedirectAttributes redirectAttributes) {

        customerService.editCustomer(customer, customer.getCustomerId());

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa phương tiện thành công!");
        return "redirect:/personal-info";
    }

}
