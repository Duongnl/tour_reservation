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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class PersonalInfoController {
    @Autowired
    private CustomerService customerService;
        @GetMapping("/personal-info")
    public String index(Model model) {
        model.addAttribute("customer", customerService.getCustomer("2"));
        return "client/customer/index.html";
    }
    @GetMapping("/personal-info/edit/{slug}")
    public String EditView(Model model,@PathVariable("slug") String slug) throws ParseException {

        Customer customer = customerService.getCustomer(slug);
//        System.out.println("slug: " + customer.getBirthday());
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//        LocalDate localDate = LocalDate.parse(customer.getBirthday().toString(), formatter);
//        customer.setBirthday(localDate);



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
