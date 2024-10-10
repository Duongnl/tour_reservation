package com.group21.tour_reservation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/admin/customer")
    public String transport(Model model) {
        model.addAttribute("customers", customerService.getAllCustomer());
        return "admin/customer/customer.html";
    }

    @GetMapping("/admin/customer/add")
    public String customerAddView(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer); // Thêm đối tượng vào mô hình
        return "admin/customer/customer-add.html";
    }

    @PostMapping("/admin/customer/add-customer")
    public String addCustomer(Model model, @ModelAttribute("customer") Customer customer,
            RedirectAttributes redirectAttributes) {
        customerService.createCustomer(customer);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới khách hàng thành công!");
        return "redirect:/admin/customer";
    }

    @GetMapping("/admin/customer/{slug}")
    public String customerEditView(Model model, @PathVariable("slug") String slug) {
        Customer customer = customerService.getCustomer(slug);
        if (customer == null) {
            return "admin/404.html";
        }

        model.addAttribute("customer", customer); // Thêm đối tượng vào mô hình
        return "admin/customer/customer-edit.html";
    }

    @PostMapping("/admin/customer/edit-customer")
    public String editCustomer(Model model, @ModelAttribute("customer") Customer customer,
            RedirectAttributes redirectAttributes) {
        customerService.editCustomer(customer);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa khách hàng thành công!");
        return "redirect:/admin/customer";
    }
    
    @GetMapping("/admin/customer/delete-customer/{id}")
    public String deleteCustomerID(Model model, @PathVariable("id") String customerId,
            RedirectAttributes redirectAttributes) {
        if (customerService.deleteCustomer(customerId) != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công!");
        } else {
            return "admin/404.html";
        }
        return "redirect:/admin/customer";
    }

}
