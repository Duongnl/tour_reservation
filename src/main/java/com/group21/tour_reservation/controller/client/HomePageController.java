package com.group21.tour_reservation.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Customer;

import com.group21.tour_reservation.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomePageController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String getHomePage(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        System.out.println(">>> check id" + session.getAttribute("id"));
        return "client/home.html";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        Account account = new Account();
        Customer customer = new Customer();
        customer.setAccount(account);
        model.addAttribute("customer", customer);
        return "client/auth/register.html";
    }

    @PostMapping("/register")
    public String createAccount(@ModelAttribute("customer") Customer customer,
            RedirectAttributes redirectAttributes) {

        customerService.createAccount(customer);

        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới khách hàng thành công!");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login.html";
    }

    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {
       
        return "client/auth/deny.html";}
        
    @GetMapping("/tour")
    public String tourView() {

        return "client/tour-page.html";
    }
}
