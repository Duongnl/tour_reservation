package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.entity.Account;
import com.group21.tour_reservation.entity.Customer;
import com.group21.tour_reservation.service.AccountService;
import com.group21.tour_reservation.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountInfoController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/account-info")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object idObj = session.getAttribute("id");
        model.addAttribute("account", accountService.getAccount(String.valueOf(idObj)));
        return "client/account/index.html";
    }
    @GetMapping("/account-info/edit/{slug}")
    public String EditView(Model model,@PathVariable("slug") String slug) {
        Account account = accountService.getAccount(slug);
        if (account == null) {
            return "admin/404.html";
        }

        model.addAttribute("account", account); // Thêm đối tượng vào mô hình
        return "client/account/edit.html";
    }
    @PostMapping("/account-info/edit")
    public String edit(Model model, @ModelAttribute("account") Account account,
                       RedirectAttributes redirectAttributes) {

        accountService.editAccount(account);

        // Thêm thông báo vào RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa phương tiện thành công!");
        return "redirect:/account-info";
    }

}
