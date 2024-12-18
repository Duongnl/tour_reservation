package com.group21.tour_reservation.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<Customer> customersWithoutRep = customerService.findCustomersWithNullRelationshipId();
        model.addAttribute("customers", customersWithoutRep);
        model.addAttribute("customer", customer); // Thêm đối tượng vào mô hình
        return "admin/customer/customer-add.html";
    }

    @PostMapping("/admin/customer/add-customer")
    public String addCustomer(Model model, @ModelAttribute("customer") Customer customer,
            @RequestParam(value = "selectedCustomerId", required = false) Integer selectedCustomerId, // Đổi thành Integer
            RedirectAttributes redirectAttributes) {
    
        // Kiểm tra nếu selectedCustomerId không null
        if (selectedCustomerId != null) {
            customerService.createCustomer(customer, selectedCustomerId);
        } else {
            customerService.createCustomer(customer, 0); // Hoặc xử lý khác nếu không chọn chủ hộ
        }
    
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới khách hàng thành công!");
        return "redirect:/admin/customer";
    }
    

    @GetMapping("/admin/customer/{slug}")
    public String customerEditView(Model model, @PathVariable("slug") String slug) {
        Customer customer = customerService.getCustomer(slug);
        if (customer == null) {
            return "admin/404.html";
        }
        List<Customer> customersWithoutRep = customerService.findCustomersWithNullRelationshipId();

         // Lấy danh sách khách hàng có relationship_id trùng với customerId của khách hàng hiện tại
         List<Customer> relatedCustomers = customerService.getCustomersByRelationshipId(customer.getCustomerId());
        model.addAttribute("customers", customersWithoutRep);
        model.addAttribute("relatedCustomers", relatedCustomers); // Khách hàng liên quan
        model.addAttribute("customer", customer); // Thêm đối tượng vào mô hình
        return "admin/customer/customer-edit.html";
    }

    @PostMapping("/admin/customer/edit-customer")
    public String editCustomer(Model model, @ModelAttribute("customer") Customer customer,
            @RequestParam(value = "selectedCustomerId", required = false) Integer selectedCustomerId,
            RedirectAttributes redirectAttributes) {
        customerService.editCustomer(customer, selectedCustomerId);

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
